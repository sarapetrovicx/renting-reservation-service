package com.raf.rentingreservationservice.controller;

import com.raf.rentingreservationservice.domain.Availability;
import com.raf.rentingreservationservice.dto.AvailabilityDto;
import com.raf.rentingreservationservice.dto.CompanyVehicleCreateDto;
import com.raf.rentingreservationservice.dto.CompanyVehicleDto;
import com.raf.rentingreservationservice.service.CompanyVehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companyvehicle")
public class CompanyVehicleController {

    private CompanyVehicleService companyVehicleService;

    public CompanyVehicleController(CompanyVehicleService companyVehicleService) {
        this.companyVehicleService = companyVehicleService;
    }
    @GetMapping
    private ResponseEntity<Page<CompanyVehicleDto>> getAllVehicles(Pageable pageable){
        return new ResponseEntity<>(companyVehicleService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/available")
    private ResponseEntity<Page<CompanyVehicleDto>> getAvailableVehicles(Pageable pageable,@RequestBody AvailabilityDto availability){
        return new ResponseEntity<>(companyVehicleService.findAvailable(pageable, availability), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<CompanyVehicleDto> addVehicle(@RequestBody CompanyVehicleCreateDto createVehicleDto){
        return new ResponseEntity<>(companyVehicleService.add(createVehicleDto), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<CompanyVehicleDto> editVehicle(@PathVariable("id") Long id, @RequestBody CompanyVehicleCreateDto createVehicleDto){
        return new ResponseEntity<>(companyVehicleService.edit(id, createVehicleDto), HttpStatus.ACCEPTED);
    }
}
