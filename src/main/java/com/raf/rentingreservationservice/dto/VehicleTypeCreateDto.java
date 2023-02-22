package com.raf.rentingreservationservice.dto;

import com.raf.rentingreservationservice.domain.Vehicle;

public class VehicleTypeCreateDto {

    private String name;
    private Long vehicle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }
}
