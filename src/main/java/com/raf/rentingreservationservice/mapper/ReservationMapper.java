package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.Reservation;
import com.raf.rentingreservationservice.domain.Review;
import com.raf.rentingreservationservice.dto.ReservationCreateDto;
import com.raf.rentingreservationservice.dto.ReservationDto;
import com.raf.rentingreservationservice.dto.ReviewCreateDto;
import com.raf.rentingreservationservice.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDto reservationToreservationDto (Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUserId());
        reservationDto.setCompanyVehicleId(reservation.getCompanyVehicleId());
        reservationDto.setPriceWithDiscount(reservation.getPriceWithDiscount());
        reservationDto.setAvailability(reservation.getAvailabilityId());
        reservationDto.setCancelled(reservation.isCancelled());
        return reservationDto;
    }

    public Reservation reservationCreateDtoToReservation(ReservationCreateDto reservationCreateDto){
        Reservation reservation = new Reservation();
        reservation.setUserId(reservationCreateDto.getUserId());
        reservation.setCompanyVehicleId(reservationCreateDto.getCompanyVehicleId());
        reservation.setAvailabilityId(reservationCreateDto.getAvailability());
        reservation.setCancelled(false);
        return reservation;
    }


}
