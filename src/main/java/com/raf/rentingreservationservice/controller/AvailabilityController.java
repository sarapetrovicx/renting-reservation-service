package com.raf.rentingreservationservice.controller;


import com.raf.rentingreservationservice.dto.AvailabilityCreateDto;
import com.raf.rentingreservationservice.dto.AvailabilityDto;
import com.raf.rentingreservationservice.service.AvailabilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public ResponseEntity<Page<AvailabilityDto>> getAvailabilities(@RequestHeader("Authorization") String authorization,
                                                                 Pageable pageable) {
        return new ResponseEntity<>(availabilityService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AvailabilityDto> saveAvailability(@RequestBody AvailabilityCreateDto availabilityCreateDto) {
        return new ResponseEntity<>(availabilityService.add(availabilityCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AvailabilityDto> editCompany (@PathVariable("id") Long id, @RequestBody AvailabilityCreateDto availabilityCreateDto){
        return new ResponseEntity<>(availabilityService.edit(id, availabilityCreateDto), HttpStatus.ACCEPTED);
    }
}
