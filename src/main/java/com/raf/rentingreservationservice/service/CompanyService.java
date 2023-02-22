package com.raf.rentingreservationservice.service;

import com.raf.rentingreservationservice.dto.CompanyDto;
import com.raf.rentingreservationservice.dto.CompanyCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Page<CompanyDto> findAll(Pageable pageable);

    CompanyDto add(CompanyCreateDto companyEditDto);

    CompanyDto edit(Long id, CompanyCreateDto clientCreateDto);

}
