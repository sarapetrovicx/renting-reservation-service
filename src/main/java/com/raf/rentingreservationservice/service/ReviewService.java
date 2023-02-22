package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.ReviewCreateDto;
import com.raf.rentingreservationservice.dto.ReviewDto;
import com.raf.rentingreservationservice.dto.VehicleCreateDto;
import com.raf.rentingreservationservice.dto.VehicleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Page<ReviewDto> findAll(Pageable pageable);

    ReviewDto add(ReviewCreateDto reviewCreateDto);

    ReviewDto edit(Long id, ReviewCreateDto reviewCreateDto);
}
