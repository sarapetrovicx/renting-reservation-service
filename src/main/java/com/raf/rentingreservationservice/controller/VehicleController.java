package com.raf.rentingreservationservice.controller;

import com.raf.rentingreservationservice.dto.VehicleCreateDto;
import com.raf.rentingreservationservice.dto.VehicleDto;
import com.raf.rentingreservationservice.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    private ResponseEntity<Page<VehicleDto>> getAllVehicles(Pageable pageable){
        return new ResponseEntity<>(vehicleService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleCreateDto createVehicleDto){
        return new ResponseEntity<>(vehicleService.add(createVehicleDto), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<VehicleDto> editVehicle(@PathVariable("id") Long id, @RequestBody VehicleCreateDto createVehicleDto){
        return new ResponseEntity<>(vehicleService.edit(id, createVehicleDto), HttpStatus.ACCEPTED);
    }
}
