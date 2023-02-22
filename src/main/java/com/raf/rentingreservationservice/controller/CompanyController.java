package com.raf.rentingreservationservice.controller;

import com.raf.rentingreservationservice.dto.CompanyDto;
import com.raf.rentingreservationservice.dto.CompanyCreateDto;
import com.raf.rentingreservationservice.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(
                                                       Pageable pageable) {
        return new ResponseEntity<>(companyService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    //moze menadzer i admin
    public ResponseEntity<CompanyDto> saveCompany(@RequestBody CompanyCreateDto companyEditDto) {
        return new ResponseEntity<>(companyService.add(companyEditDto), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    //moze menadzer i admin
    public ResponseEntity<CompanyDto> editCompany (@PathVariable("id") Long id, @RequestBody CompanyCreateDto companyEditDto){
        return new ResponseEntity<>(companyService.edit(id, companyEditDto), HttpStatus.ACCEPTED);
    }
}
