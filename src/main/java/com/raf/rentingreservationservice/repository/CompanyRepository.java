package com.raf.rentingreservationservice.repository;

import com.raf.rentingreservationservice.domain.Company;
import com.raf.rentingreservationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByNameAndCity(String name, String city);

}
