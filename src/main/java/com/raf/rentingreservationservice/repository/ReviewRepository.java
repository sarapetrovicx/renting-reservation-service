package com.raf.rentingreservationservice.repository;

import com.raf.rentingreservationservice.domain.Review;
import com.raf.rentingreservationservice.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
