package com.raf.rentingreservationservice.repository;

import com.raf.rentingreservationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByUserIdAndCompanyVehicleId(Long userId, Long projectionId);
}
