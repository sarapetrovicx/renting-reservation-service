package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.domain.Availability;
import com.raf.rentingreservationservice.domain.CompanyVehicle;
import com.raf.rentingreservationservice.domain.Reservation;
import com.raf.rentingreservationservice.domain.Vehicle;
import com.raf.rentingreservationservice.dto.AvailabilityDto;
import com.raf.rentingreservationservice.dto.CompanyCreateDto;
import com.raf.rentingreservationservice.dto.CompanyVehicleCreateDto;
import com.raf.rentingreservationservice.dto.CompanyVehicleDto;
import com.raf.rentingreservationservice.exception.NotFoundException;
import com.raf.rentingreservationservice.mapper.CompanyVehicleMapper;
import com.raf.rentingreservationservice.repository.AvailabilityRepository;
import com.raf.rentingreservationservice.repository.CompanyRepository;
import com.raf.rentingreservationservice.repository.CompanyVehicleRepository;
import com.raf.rentingreservationservice.repository.ReservationRepository;
import com.raf.rentingreservationservice.service.CompanyVehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class CompanyVehicleServiceImpl implements CompanyVehicleService {

    CompanyVehicleRepository companyVehicleRepository;
    CompanyVehicleMapper companyVehicleMapper;

    ReservationRepository reservationRepository;
    AvailabilityRepository availabilityRepository;

    CompanyRepository companyRepository;

    public CompanyVehicleServiceImpl(CompanyVehicleRepository companyVehicleRepository, CompanyVehicleMapper companyVehicleMapper, ReservationRepository reservationRepository, AvailabilityRepository availabilityRepository, CompanyRepository companyRepository) {
        this.companyVehicleRepository = companyVehicleRepository;
        this.companyVehicleMapper = companyVehicleMapper;
        this.reservationRepository = reservationRepository;
        this.availabilityRepository = availabilityRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<CompanyVehicleDto> findAll(Pageable pageable) {
        return  companyVehicleRepository.findAll(pageable)
                .map(companyVehicleMapper::companyVehicleToCompanyVehicleDto);
    }

    @Override
    public Page<CompanyVehicleDto> findAvailable(Pageable pageable, AvailabilityDto availability) {
        Stream<CompanyVehicleDto> res = companyVehicleRepository.findAll().stream().filter(companyVehicle ->
                reservationRepository.findAll(pageable).stream()
                        .anyMatch(reservation -> (availabilityRepository.findById(reservation.getAvailabilityId()).get().getStartDate().before(availability.getStartDate())||
                                availabilityRepository.findById(reservation.getAvailabilityId()).get().getEndDate().after(availability.getEndDate())) &&
                                !reservation.isCancelled() && reservation.getCompanyVehicleId() == companyVehicle.getId())).map(companyVehicleMapper::companyVehicleToCompanyVehicleDto);

        return new PageImpl<>(res.collect(Collectors.toList()));
    }

    @Override
    public CompanyVehicleDto add(CompanyVehicleCreateDto createVehicleDto) {
        CompanyVehicle companyVehicle= companyVehicleMapper.companyCreateVehicleToCompanyVehicle(createVehicleDto);
        companyVehicleRepository.save(companyVehicle);
        int sumOfVehicle  = companyRepository.findById(companyVehicle.getId()).get().getNumOfVehicles();
        companyRepository.findById(companyVehicle.getId()).get().setNumOfVehicles(sumOfVehicle+1);
        return companyVehicleMapper.companyVehicleToCompanyVehicleDto(companyVehicle);
    }

    @Override
    public CompanyVehicleDto edit(Long id, CompanyVehicleCreateDto createVehicleDto) {
        CompanyVehicle companyVehicle = companyVehicleRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Availability with %d id does not exist.", id)));
        companyVehicle.setCompany(createVehicleDto.getCompany());
        companyVehicle.setVehicle(createVehicleDto.getVehicle());
        companyVehicle.setPrice(createVehicleDto.getPrice());
        companyVehicleRepository.save(companyVehicle);
        return companyVehicleMapper.companyVehicleToCompanyVehicleDto(companyVehicle);
    }
}
