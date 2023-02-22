package com.raf.rentingreservationservice.controller;

import com.raf.rentingreservationservice.dto.VehicleTypeCreateDto;
import com.raf.rentingreservationservice.dto.VehicleTypeDto;
import com.raf.rentingreservationservice.service.VehicleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicleType")
public class VehicleTypeController {

    private VehicleTypeService vehicleTypeService;


    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<VehicleTypeDto>> getAllVehicleTypes(@RequestHeader("Authorization") String authorization,
                                                                Pageable pageable) {
        return new ResponseEntity<>(vehicleTypeService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehicleTypeDto> saveVehicleType(@RequestBody VehicleTypeCreateDto vehicleTypeCreateDto) {
        return new ResponseEntity<>(vehicleTypeService.add(vehicleTypeCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VehicleTypeDto> editVehicleType(@PathVariable("id") Long id, @RequestBody VehicleTypeCreateDto vehicleTypeCreateDto){
        return new ResponseEntity<>(vehicleTypeService.edit(id, vehicleTypeCreateDto), HttpStatus.ACCEPTED);
    }

}
