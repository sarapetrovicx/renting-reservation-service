package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.VehicleType;
import com.raf.rentingreservationservice.dto.VehicleTypeCreateDto;
import com.raf.rentingreservationservice.dto.VehicleTypeDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {

    public VehicleTypeMapper() {
    }

    public VehicleTypeDto vehicleTypeToVehicleTypeDto(VehicleType vehicleType){
        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        vehicleTypeDto.setId(vehicleType.getId());
        vehicleTypeDto.setName(vehicleType.getName());
//        vehicleTypeDto.setVehicle(vehicleType.getVehicle());
        return vehicleTypeDto;
    }

    public VehicleType vehicleTypeCreateDtoToVehicleType(VehicleTypeCreateDto vehicleTypeCreateDto){
        VehicleType vehicleType = new VehicleType();
        vehicleType.setName(vehicleTypeCreateDto.getName());
//        vehicleType.setVehicle(vehicleTypeCreateDto.getVehicle());
        return vehicleType;
    }

}
