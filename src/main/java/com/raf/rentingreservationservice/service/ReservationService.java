package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ReservationService {
    Page<ReservationDto> findAll(Pageable pageable);

    ReservationDto add(ReservationCreateDto createVehicleDto);

    ReservationDto edit(Long id, ReservationEditDto reservationEditDto);

    ReservationDto cancel(Long id);

    AccommodationListDto findAllAvailable(String city, String company, String dateFrom, String dateTo);

    ReservationListDto findUserReservations(Long userId);
}
