package com.raf.rentingreservationservice.dto;

import java.math.BigDecimal;

public class ReservationEditDto {

    private Long userId;
    private Long companyVehicleId;
    private BigDecimal priceWithDiscount;
    private Long availability;
    private boolean cancelled;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyVehicleId() {
        return companyVehicleId;
    }

    public void setCompanyVehicleId(Long companyVehicleId) {
        this.companyVehicleId = companyVehicleId;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public Long getAvailability() {
        return availability;
    }

    public void setAvailability(Long availability) {
        this.availability = availability;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
