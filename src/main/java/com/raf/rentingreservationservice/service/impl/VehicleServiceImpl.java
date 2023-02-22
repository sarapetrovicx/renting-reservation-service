package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.domain.Vehicle;
import com.raf.rentingreservationservice.dto.VehicleCreateDto;
import com.raf.rentingreservationservice.dto.VehicleDto;
import com.raf.rentingreservationservice.mapper.VehicleMapper;
import com.raf.rentingreservationservice.repository.VehicleRepository;
import com.raf.rentingreservationservice.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public Page<VehicleDto> findAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable)
                .map(vehicleMapper::vehicleToVehicleDto);
    }

    @Override
    public VehicleDto add(VehicleCreateDto createVehicleDto) {
        Vehicle vehicle = vehicleMapper.vehicleCreateDtoToVehicle(createVehicleDto);
        vehicleRepository.save(vehicle);
        return vehicleMapper.vehicleToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto edit(Long id, VehicleCreateDto createVehicleDto) {
        return null;
    }
}
