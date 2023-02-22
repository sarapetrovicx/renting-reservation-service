package com.raf.rentingreservationservice.mapper;

import com.raf.rentingreservationservice.domain.Availability;
import com.raf.rentingreservationservice.dto.AvailabilityCreateDto;
import com.raf.rentingreservationservice.dto.AvailabilityDto;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {

    public AvailabilityMapper() {
    }

    public AvailabilityDto availabilityToAvailabilityDto(Availability availability){
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setId(availability.getId());
        availabilityDto.setStartDate(availability.getStartDate());
        availabilityDto.setEndDate(availability.getEndDate());
        return availabilityDto;
    }

    public Availability availabilityCreateDtoToAvailability(AvailabilityCreateDto availabilityCreateDto){
        Availability availability = new Availability();
        availability.setStartDate(availabilityCreateDto.getStartDate());
        availability.setEndDate(availabilityCreateDto.getEndDate());
        return availability;
    }
}
