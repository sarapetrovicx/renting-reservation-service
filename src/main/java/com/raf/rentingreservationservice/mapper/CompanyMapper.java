package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.Company;
import com.raf.rentingreservationservice.dto.CompanyDto;
import com.raf.rentingreservationservice.dto.CompanyCreateDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyMapper() {
    }

    public CompanyDto companyToCompanyDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setDescription(company.getDescription());
        companyDto.setNumOfVehicles(company.getNumOfVehicles());
        companyDto.setCity(company.getCity());
        companyDto.setManagerId(company.getManagerID());
        return companyDto;
    }

    public Company companyEditDtoToCompany(CompanyCreateDto companyCreateDto){
        Company company = new Company();
        company.setName(companyCreateDto.getName());
        company.setDescription(companyCreateDto.getDescription());
        company.setCity(companyCreateDto.getCity());
        company.setNumOfVehicles(companyCreateDto.getNumOfVehicles());
        company.setManagerID(companyCreateDto.getManagerId());
        return company;
    }




}
