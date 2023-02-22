package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.CompanyVehicle;
import com.raf.rentingreservationservice.domain.Review;
import com.raf.rentingreservationservice.dto.CompanyVehicleCreateDto;
import com.raf.rentingreservationservice.dto.CompanyVehicleDto;
import com.raf.rentingreservationservice.dto.ReviewCreateDto;
import com.raf.rentingreservationservice.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDto reviewToReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setComment(review.getComment());
        reviewDto.setRating(review.getRating());
        reviewDto.setUserId(review.getUserId());
        reviewDto.setCompany(review.getCompany());
        return reviewDto;
    }

    public Review reviewCreateDtoToReview(ReviewCreateDto reviewCreateDto){
        Review review = new Review();
        review.setCompany(reviewCreateDto.getCompany());
        review.setComment(reviewCreateDto.getComment());
        review.setRating(reviewCreateDto.getRating());
        review.setUserId(review.getUserId());
        return review;
    }
}
