package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyVehicleService {
    Page<CompanyVehicleDto> findAll(Pageable pageable);

    Page<CompanyVehicleDto> findAvailable(Pageable pageable, AvailabilityDto availabilityDto);

    CompanyVehicleDto add(CompanyVehicleCreateDto createVehicleDto);

    CompanyVehicleDto edit(Long id, CompanyVehicleCreateDto createVehicleDto);
}
