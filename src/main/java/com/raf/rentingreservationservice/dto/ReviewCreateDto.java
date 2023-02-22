package com.raf.rentingreservationservice.dto;

import com.raf.rentingreservationservice.domain.Company;

public class ReviewCreateDto {


    private Long userId;
    private Company company;
    private int rating;
    private String comment;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
