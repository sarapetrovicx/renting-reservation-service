package com.raf.rentingreservationservice.comunication.client.dto;

public class ClientRentDaysDto {
    private int days;
    private Long clientId;

    public ClientRentDaysDto(int days, Long clientId) {
        this.days = days;
        this.clientId = clientId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
