package com.raf.rentingreservationservice.repository;

import com.raf.rentingreservationservice.domain.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleTypeRepository  extends JpaRepository<VehicleType, Long> {
}
