package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.Vehicle;
import com.raf.rentingreservationservice.dto.VehicleCreateDto;
import com.raf.rentingreservationservice.dto.VehicleDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleDto vehicleToVehicleDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setManufacturer(vehicle.getManufacturer());
        vehicleDto.setModel(vehicle.getModel());
        return vehicleDto;
    }

    public VehicleCreateDto vehicleToCreateVehicleDto(Vehicle vehicle){
        VehicleCreateDto vehicleDto = new VehicleCreateDto();
        vehicleDto.setManufacturer(vehicle.getManufacturer());
        vehicleDto.setModel(vehicle.getModel());
        return vehicleDto;
    }

    public Vehicle vehicleCreateDtoToVehicle(VehicleCreateDto createVehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setManufacturer(createVehicleDto.getManufacturer());
        vehicle.setModel(createVehicleDto.getModel());
        return vehicle;
    }

    public Vehicle vehicleDtoToVehicle(VehicleDto createVehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(createVehicleDto.getId());
        vehicle.setManufacturer(createVehicleDto.getManufacturer());
        vehicle.setModel(createVehicleDto.getModel());
        return vehicle;
    }


}
