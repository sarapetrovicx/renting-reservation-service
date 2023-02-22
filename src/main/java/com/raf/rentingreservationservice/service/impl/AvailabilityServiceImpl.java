package com.raf.rentingreservationservice.service.impl;

import com.raf.rentingreservationservice.domain.Availability;
import com.raf.rentingreservationservice.dto.AvailabilityCreateDto;
import com.raf.rentingreservationservice.dto.AvailabilityDto;
import com.raf.rentingreservationservice.exception.NotFoundException;
import com.raf.rentingreservationservice.mapper.AvailabilityMapper;
import com.raf.rentingreservationservice.repository.AvailabilityRepository;
import com.raf.rentingreservationservice.service.AvailabilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

    private AvailabilityMapper availabilityMapper;
    private AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityMapper availabilityMapper, AvailabilityRepository availabilityRepository) {
        this.availabilityMapper = availabilityMapper;
        this.availabilityRepository = availabilityRepository;
    }


    @Override
    public Page<AvailabilityDto> findAll(Pageable pageable) {
        return availabilityRepository.findAll(pageable)
                .map(availabilityMapper::availabilityToAvailabilityDto);
    }

    @Override
    public AvailabilityDto add(AvailabilityCreateDto availabilityCreateDto) {
        Availability availability = availabilityMapper.availabilityCreateDtoToAvailability(availabilityCreateDto);
        availabilityRepository.save(availability);
        return availabilityMapper.availabilityToAvailabilityDto(availability);
    }

    @Override
    public AvailabilityDto edit(Long id, AvailabilityCreateDto availabilityCreateDto) {
        Availability availability = availabilityRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Availability with %d id does not exist.", id)));
        availability.setStartDate(availabilityCreateDto.getStartDate());
        availability.setEndDate(availabilityCreateDto.getEndDate());
        availabilityRepository.save(availability);
        return availabilityMapper.availabilityToAvailabilityDto(availability);
    }
}
