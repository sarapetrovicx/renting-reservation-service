package com.raf.rentingreservationservice.controller;

import com.raf.rentingreservationservice.dto.ReviewCreateDto;
import com.raf.rentingreservationservice.dto.ReviewDto;
import com.raf.rentingreservationservice.dto.VehicleCreateDto;
import com.raf.rentingreservationservice.dto.VehicleDto;
import com.raf.rentingreservationservice.service.ReviewService;
import com.raf.rentingreservationservice.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(VehicleService vehicleService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    private ResponseEntity<Page<ReviewDto>> getAllReviews(Pageable pageable){
        return new ResponseEntity<>(reviewService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ReviewDto> addReview(@RequestBody ReviewCreateDto reviewCreateDto){
        return new ResponseEntity<>(reviewService.add(reviewCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<ReviewDto> editReview(@PathVariable("id") Long id, @RequestBody ReviewCreateDto reviewCreateDto){
        return new ResponseEntity<>(reviewService.edit(id, reviewCreateDto), HttpStatus.ACCEPTED);
    }
}
