package com.raf.rentingreservationservice.repository;

import com.raf.rentingreservationservice.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    Optional<Availability> findByStartDateAndEndDate(Date startDate, Date endDate);

}
