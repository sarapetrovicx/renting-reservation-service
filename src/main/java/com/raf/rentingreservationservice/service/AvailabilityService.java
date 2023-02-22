package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.AvailabilityCreateDto;
import com.raf.rentingreservationservice.dto.AvailabilityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvailabilityService {

    Page<AvailabilityDto> findAll(Pageable pageable);

    AvailabilityDto add(AvailabilityCreateDto availabilityCreateDto);

    AvailabilityDto edit(Long id, AvailabilityCreateDto availabilityCreateDto);
}
