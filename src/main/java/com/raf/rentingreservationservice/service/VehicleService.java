package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.VehicleCreateDto;
import com.raf.rentingreservationservice.dto.VehicleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    Page<VehicleDto> findAll(Pageable pageable);

    VehicleDto add(VehicleCreateDto createVehicleDto);

    VehicleDto edit(Long id, VehicleCreateDto createVehicleDto);
}
