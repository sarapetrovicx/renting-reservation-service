package com.raf.rentingreservationservice.dto;

import com.raf.rentingreservationservice.domain.Vehicle;

import javax.persistence.OneToMany;

public class VehicleTypeDto {

    private Long id;
    private String name;
    private Long vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
