package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.domain.Company;
import com.raf.rentingreservationservice.dto.CompanyDto;
import com.raf.rentingreservationservice.dto.CompanyCreateDto;
import com.raf.rentingreservationservice.exception.NotFoundException;
import com.raf.rentingreservationservice.mapper.CompanyMapper;
import com.raf.rentingreservationservice.repository.CompanyRepository;
import com.raf.rentingreservationservice.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private CompanyMapper companyMapper;
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyMapper companyMapper, CompanyRepository companyRepository) {
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
    }


    @Override
    public Page<CompanyDto> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(companyMapper::companyToCompanyDto);
    }

    @Override
    public CompanyDto add(CompanyCreateDto companyEditDto) {
        Company company = companyMapper.companyEditDtoToCompany(companyEditDto);
        companyRepository.save(company);
        return companyMapper.companyToCompanyDto(company);
    }

    @Override
    public CompanyDto edit(Long id, CompanyCreateDto companyCreateDto) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Company named %s does not exist.", companyCreateDto.getName())));
        company.setName(companyCreateDto.getName());
        company.setCity(companyCreateDto.getCity());
        company.setDescription(companyCreateDto.getDescription());
        company.setNumOfVehicles(companyCreateDto.getNumOfVehicles());
        company.setManagerID(companyCreateDto.getManagerId());
        companyRepository.save(company);
        return companyMapper.companyToCompanyDto(company);
    }
}
