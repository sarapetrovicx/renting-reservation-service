package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.comunication.client.dto.ClientDto;
import com.raf.rentingreservationservice.comunication.client.dto.ClientRentDaysDto;
import com.raf.rentingreservationservice.comunication.notification.dto.NotificationDto;
import com.raf.rentingreservationservice.domain.*;
import com.raf.rentingreservationservice.dto.*;
import com.raf.rentingreservationservice.exception.NotFoundException;
import com.raf.rentingreservationservice.listener.helper.MessageHelper;
import com.raf.rentingreservationservice.mapper.ReservationMapper;
import com.raf.rentingreservationservice.repository.*;
import com.raf.rentingreservationservice.service.ReservationService;
import com.raf.rentingreservationservice.comunication.client.dto.DiscountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    CompanyVehicleRepository companyVehicleRepository;
    AvailabilityRepository availabilityRepository;
    ReservationMapper reservationMapper;
    CompanyRepository companyRepository;
    VehicleRepository vehicleRepository;
    VehicleTypeRepository vehicleTypeRepository;


    RestTemplate userServiceRestTemplate;

    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String notification;
    private String rentDays;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  CompanyRepository companyRepository,
                                  VehicleRepository vehicleRepository,
                                  CompanyVehicleRepository companyVehicleRepository,
                                  AvailabilityRepository availabilityRepository,
                                  VehicleTypeRepository vehicleTypeRepository,
                                  ReservationMapper reservationMapper,
                                  RestTemplate userServiceRestTemplate,
                                  JmsTemplate jmsTemplate,
                                  MessageHelper messageHelper,
                                  @Value("notification_destination")String notification,
                                  @Value("rentDays_destination")String rentDays) {
        this.reservationRepository = reservationRepository;
        this.companyVehicleRepository = companyVehicleRepository;
        this.availabilityRepository = availabilityRepository;
        this.reservationMapper = reservationMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.notification = notification;
        this.rentDays = rentDays;
        this.companyRepository = companyRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable)
                .map(reservationMapper::reservationToreservationDto);
    }

    @Override
    public ReservationDto add(ReservationCreateDto reservationCreateDto) {
        //get price from companyVehicle service

        //increment rental date for user
        long diff = availabilityRepository.findById(reservationCreateDto.getAvailability()).get().getStartDate().getTime() - availabilityRepository.findById(reservationCreateDto.getAvailability()).get().getEndDate().getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        ClientRentDaysDto clientRentDaysDto = new ClientRentDaysDto( (int)days, reservationCreateDto.getUserId());

        jmsTemplate.convertAndSend(rentDays, messageHelper.createTextMessage(clientRentDaysDto));

        //get user from user service (for notification)
        ResponseEntity<ClientDto> clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
                reservationCreateDto.getUserId(), HttpMethod.GET, null, ClientDto.class);
        //send notification
        NotificationDto notification = new NotificationDto();
        notification.setRecever(clientDtoResponseEntity.getBody().getEmail());

        CompanyVehicle companyVehicle = companyVehicleRepository.findById(reservationCreateDto.getCompanyVehicleId()).get();
        Company company = companyRepository.findById(companyVehicle.getCompany()).get();
        Vehicle vehicle = vehicleRepository.findById(companyVehicle.getVehicle()).get();

        String params = clientDtoResponseEntity.getBody().getFirstName() + ", " + clientDtoResponseEntity.getBody().getLastName() + ", " +
                company.getName() + ", " +
                vehicle.getManufacturer()  + ", " +
                vehicle.getModel() + ", " +
                companyVehicleRepository.findById(reservationCreateDto.getCompanyVehicleId()).get().getPrice().toString() + ", " +
                availabilityRepository.findById(reservationCreateDto.getAvailability()).get().getStartDate().toString() + ", " +
                availabilityRepository.findById(reservationCreateDto.getAvailability()).get().getEndDate().toString();
        notification.setParameters(params);
        notification.setType("Reservation");

        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));


        //get discount from user service
        ResponseEntity<DiscountDto> discountDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
                reservationCreateDto.getUserId() + "/discount", HttpMethod.GET, null, DiscountDto.class);
        //calculate price with discount
        BigDecimal price = companyVehicle.getPrice().divide(BigDecimal.valueOf(100))
                                .multiply(BigDecimal.valueOf(100 - discountDtoResponseEntity.getBody().getDiscount()));
        Reservation reservation = reservationMapper.reservationCreateDtoToReservation(reservationCreateDto);
        reservation.setPriceWithDiscount(price.multiply(BigDecimal.valueOf(days)));
        reservationRepository.save(reservation);
        return reservationMapper.reservationToreservationDto(reservation);
    }

    @Override
    public ReservationDto edit(Long id, ReservationEditDto reservationEditDto) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Reservation with %d id does not exist.", id)));
        reservation.setUserId(reservationEditDto.getUserId());
        reservation.setCompanyVehicleId(reservationEditDto.getCompanyVehicleId());
        reservation.setCancelled(reservationEditDto.isCancelled());
        reservation.setAvailabilityId(reservationEditDto.getAvailability());
        reservationRepository.save(reservation);
        return reservationMapper.reservationToreservationDto(reservation);
    }

    @Override
    public ReservationDto cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Reservation with %d id does not exist.", id)));

        ResponseEntity<ClientDto> clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
                id, HttpMethod.GET, null, ClientDto.class);

        //decrease rental days for user
        long diff = availabilityRepository.findById(reservation.getAvailabilityId()).get().getStartDate().getTime() - availabilityRepository.findById(reservation.getAvailabilityId()).get().getEndDate().getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        ClientRentDaysDto clientRentDaysDto = new ClientRentDaysDto( (int)days * (-1), reservation.getUserId());

        jmsTemplate.convertAndSend(rentDays, messageHelper.createTextMessage(clientRentDaysDto));

        NotificationDto notification = new NotificationDto();
        notification.setRecever(clientDtoResponseEntity.getBody().getEmail());

        String params = clientDtoResponseEntity.getBody().getFirstName() + ", " + clientDtoResponseEntity.getBody().getLastName() + ", " +
                companyRepository.findById(companyVehicleRepository.findById(reservation.getCompanyVehicleId()).get().getCompany()).get().getName() + ", " +
                vehicleRepository.findById(companyVehicleRepository.findById(reservation.getCompanyVehicleId()).get().getCompany()).get().getManufacturer()  + ", " +
                vehicleRepository.findById(companyVehicleRepository.findById(reservation.getCompanyVehicleId()).get().getCompany()).get().getModel() + ", " +
                companyVehicleRepository.findById(reservation.getCompanyVehicleId()).get().getPrice().toString() + ", " +
                availabilityRepository.findById(reservation.getAvailabilityId()).get().getStartDate().toString() + ", " +
                availabilityRepository.findById(reservation.getAvailabilityId()).get().getEndDate().toString();
        notification.setParameters(params);
        notification.setType("Cancelation");

        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));

        Long managerId = companyRepository.findById(companyVehicleRepository.findById(reservation.getCompanyVehicleId()).get().getCompany()).get().getManagerID();
        ResponseEntity<ClientDto> managerDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
                managerId, HttpMethod.GET, null, ClientDto.class);


        notification.setRecever(managerDtoResponseEntity.getBody().getEmail());


        reservationRepository.save(reservation);
        return reservationMapper.reservationToreservationDto(reservation);
    }

    @Override
    public AccommodationListDto findAllAvailable(String city, String company, String dateFrom, String dateTo) {
        Company company1 = companyRepository.findByNameAndCity(company, city).get();
//        Availability availability = availabilityRepository.findByStartDateAndEndDate(dateFrom, dateTo).get();

//        Stream<CompanyVehicle> allVehiclesInCompany = companyVehicleRepository.findAll().stream().filter(
//                companyVehicle -> companyVehicle.getCompany().equals(company1.getId())
//        )
        Date dateFromDate =Date.valueOf(dateFrom);
        Date dateTodate = Date.valueOf(dateTo);

        List<CompanyVehicle> availableVehicles = companyVehicleRepository.findAll().stream().filter(
                companyVehicle ->
                reservationRepository.findAll().stream().anyMatch(reservation ->
                        (availabilityRepository.findById(reservation.getAvailabilityId()).get().getStartDate().before(dateTodate)||
                                availabilityRepository.findById(reservation.getAvailabilityId()).get().getEndDate().before(dateFromDate))
                                && !reservation.isCancelled()
                                && reservation.getCompanyVehicleId().equals(companyVehicle.getId()))

                        && companyVehicle.getCompany().equals(company1.getId()))
                .collect(Collectors.toList());


        AccommodationListDto accommodationListDto = new AccommodationListDto();

        AccommodationDto accommodationDto = new AccommodationDto();
        accommodationDto.setCity(city);
        accommodationDto.setCompany(company);
        accommodationDto.setStartDate(dateFromDate);
        accommodationDto.setEndDate(dateTodate);

        for(CompanyVehicle companyVehicle:availableVehicles){
            Vehicle vehicle = vehicleRepository.findById(companyVehicle.getVehicle()).get();
            VehicleType vehicleType = vehicleTypeRepository.findById(vehicle.getVehicleType()).get();
            accommodationDto.setVehicle("Skoda");
            accommodationDto.setVehicleType("CUV");
//            accommodationDto.setVehicle(vehicle.getManufacturer());
//            accommodationDto.setVehicleType(vehicleType.getName());
            accommodationListDto.getContent().add(accommodationDto);
        }

//        accommodationDto.setVehicle("Skoda");
//        accommodationDto.setVehicleType("CUV");
//
//        accommodationListDto.getContent().add(accommodationDto);

        return accommodationListDto;
    }

    @Override
    public ReservationListDto findUserReservations(Long userId) {
        List<Reservation> resForUser = reservationRepository.findAll().stream().filter(reservation -> reservation.getUserId().equals(userId)).collect(Collectors.toList());

        ReservationListDto reservationListDto = new ReservationListDto();
        for(Reservation r: resForUser) {
            ReservationDto reservationDto = reservationMapper.reservationToreservationDto(r);
            reservationListDto.getContent().add(reservationDto);
        }
        return reservationListDto;
    }
}
