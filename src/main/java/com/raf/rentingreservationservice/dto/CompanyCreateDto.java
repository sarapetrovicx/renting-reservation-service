package com.raf.rentingreservationservice.dto;

public class CompanyCreateDto {

    private String name;
    private String description;
    private int numOfVehicles;
    private String city;
    private Long managerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfVehicles() {
        return numOfVehicles;
    }

    public void setNumOfVehicles(int numOfVehicles) {
        this.numOfVehicles = numOfVehicles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
