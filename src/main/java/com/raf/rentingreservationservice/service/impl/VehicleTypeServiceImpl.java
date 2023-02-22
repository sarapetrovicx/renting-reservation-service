package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.domain.VehicleType;
import com.raf.rentingreservationservice.dto.VehicleTypeCreateDto;
import com.raf.rentingreservationservice.dto.VehicleTypeDto;
import com.raf.rentingreservationservice.exception.NotFoundException;
import com.raf.rentingreservationservice.mapper.VehicleTypeMapper;
import com.raf.rentingreservationservice.repository.VehicleTypeRepository;
import com.raf.rentingreservationservice.service.VehicleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private VehicleTypeMapper vehicleTypeMapper;
    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeMapper vehicleTypeMapper, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeMapper = vehicleTypeMapper;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }


    @Override
    public Page<VehicleTypeDto> findAll(Pageable pageable) {
        return vehicleTypeRepository.findAll(pageable)
                .map(vehicleTypeMapper::vehicleTypeToVehicleTypeDto);
    }

    @Override
    public VehicleTypeDto add(VehicleTypeCreateDto vehicleTypeCreateDto) {
        VehicleType vehicleType = vehicleTypeMapper.vehicleTypeCreateDtoToVehicleType(vehicleTypeCreateDto);
        vehicleTypeRepository.save(vehicleType);
        return vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleType);
    }

    @Override
    public VehicleTypeDto edit(Long id, VehicleTypeCreateDto vehicleTypeCreateDto) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Vehicle type named %s does not exist.", vehicleTypeCreateDto.getName())));
        vehicleType.setName(vehicleTypeCreateDto.getName());
//        vehicleType.setVehicle(vehicleTypeCreateDto.getVehicle());
        vehicleTypeRepository.save(vehicleType);
        return vehicleTypeMapper.vehicleTypeToVehicleTypeDto(vehicleType);
    }
}
