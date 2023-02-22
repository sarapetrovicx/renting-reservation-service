package com.raf.rentingreservationservice.dto;

import java.util.ArrayList;
import java.util.List;

public class AccommodationListDto {

	private List<AccommodationDto> content = new ArrayList<>();

	public AccommodationListDto() {

	}

	public AccommodationListDto(List<AccommodationDto> content) {
		this.content = content;
	}

	public List<AccommodationDto> getContent() {
		return content;
	}

	public void setContent(List<AccommodationDto> content) {
		this.content = content;
	}
}
