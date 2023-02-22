package com.raf.rentingreservationservice.repository;

import com.raf.rentingreservationservice.domain.CompanyVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyVehicleRepository  extends JpaRepository<CompanyVehicle, Long> {
}
