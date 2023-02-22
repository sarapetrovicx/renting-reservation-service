package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.VehicleTypeCreateDto;
import com.raf.rentingreservationservice.dto.VehicleTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleTypeService {

    Page<VehicleTypeDto> findAll(Pageable pageable);

    VehicleTypeDto add(VehicleTypeCreateDto vehicleTypeCreateDto);

    VehicleTypeDto edit(Long id, VehicleTypeCreateDto clientCreateDto);

}
