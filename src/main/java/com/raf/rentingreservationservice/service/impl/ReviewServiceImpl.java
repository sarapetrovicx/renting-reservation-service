package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.domain.Availability;
import com.raf.rentingreservationservice.domain.Review;
import com.raf.rentingreservationservice.dto.ReviewCreateDto;
import com.raf.rentingreservationservice.dto.ReviewDto;
import com.raf.rentingreservationservice.exception.NotFoundException;
import com.raf.rentingreservationservice.mapper.ReviewMapper;
import com.raf.rentingreservationservice.repository.ReviewRepository;
import com.raf.rentingreservationservice.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public Page<ReviewDto> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::reviewToReviewDto);
    }

    @Override
    public ReviewDto add(ReviewCreateDto reviewCreateDto) {
        Review review = reviewMapper.reviewCreateDtoToReview(reviewCreateDto);
        reviewRepository.save(review);
        return reviewMapper.reviewToReviewDto(review);
    }

    @Override
    public ReviewDto edit(Long id, ReviewCreateDto reviewCreateDto) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Review with %d id does not exist.", id)));
        review.setUserId(reviewCreateDto.getUserId());
        review.setCompany(reviewCreateDto.getCompany());
        review.setRating(reviewCreateDto.getRating());
        review.setComment(reviewCreateDto.getComment());
        reviewRepository.save(review);
        return reviewMapper.reviewToReviewDto(review);
    }
}
