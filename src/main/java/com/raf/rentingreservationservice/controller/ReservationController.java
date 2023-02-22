package com.raf.rentingreservationservice.controller;

import com.raf.rentingreservationservice.dto.*;
import com.raf.rentingreservationservice.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/available")
    private ResponseEntity<AccommodationListDto> getAllAvailable(@RequestParam String city, @RequestParam String company,
                                                                @RequestParam String dateFrom, @RequestParam String dateTo){
        return new ResponseEntity<>(reservationService.findAllAvailable(city, company, dateFrom, dateTo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ReservationListDto> getForUser(@PathVariable("id") Long userId){
        return new ResponseEntity<>(reservationService.findUserReservations(userId), HttpStatus.OK);
    }

//    makeReservation(selected.getHotel(), selected.getRoomType(), selected.getStartDate(), selected.getEndDate());
    @GetMapping
    private ResponseEntity<Page<ReservationDto>> getAll(Pageable pageable){
        return new ResponseEntity<>(reservationService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ReservationDto> addNew(@RequestBody ReservationCreateDto createVehicleDto){

        return new ResponseEntity<>(reservationService.add(createVehicleDto), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<ReservationDto> edit(@PathVariable("id") Long id, @RequestBody ReservationEditDto reservationEditDto){
        return new ResponseEntity<>(reservationService.edit(id, reservationEditDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/cancel")
    private ResponseEntity<ReservationDto> cancel(@PathVariable("id") Long id){
        return new ResponseEntity<>(reservationService.cancel(id), HttpStatus.ACCEPTED);
    }
}
