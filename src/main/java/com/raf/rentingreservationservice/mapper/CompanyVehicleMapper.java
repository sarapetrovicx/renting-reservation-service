package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.Company;
import com.raf.rentingreservationservice.domain.CompanyVehicle;
import com.raf.rentingreservationservice.domain.Vehicle;
import com.raf.rentingreservationservice.dto.*;
import org.springframework.stereotype.Component;

@Component
public class CompanyVehicleMapper {

    public CompanyVehicleDto companyVehicleToCompanyVehicleDto(CompanyVehicle companyVehicle){
        CompanyVehicleDto companyVehicleDto = new CompanyVehicleDto();
        companyVehicleDto.setCompany(companyVehicle.getCompany());
        companyVehicleDto.setVehicle(companyVehicle.getVehicle());
        companyVehicleDto.setId(companyVehicle.getId());
        companyVehicleDto.setPrice(companyVehicle.getPrice());
        return companyVehicleDto;
    }

    public CompanyVehicle companyCreateVehicleToCompanyVehicle(CompanyVehicleCreateDto companyCreateDto){
        CompanyVehicle companyVehicleDto = new CompanyVehicle();
        companyVehicleDto.setCompany(companyCreateDto.getCompany());
        companyVehicleDto.setVehicle(companyCreateDto.getVehicle());
        companyVehicleDto.setPrice(companyCreateDto.getPrice());
        return companyVehicleDto;
    }
}

