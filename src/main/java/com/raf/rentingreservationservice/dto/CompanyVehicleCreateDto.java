package com.raf.rentingreservationservice.dto;

import com.raf.rentingreservationservice.domain.Company;
import com.raf.rentingreservationservice.domain.Vehicle;

import java.math.BigDecimal;

public class CompanyVehicleCreateDto {
    private Long company;
    private Long vehicle;
    private BigDecimal price;

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
