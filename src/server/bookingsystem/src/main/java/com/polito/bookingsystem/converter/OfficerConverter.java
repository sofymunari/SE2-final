package com.polito.bookingsystem.converter;


import com.polito.bookingsystem.dto.OfficerDto;
import com.polito.bookingsystem.entity.Officer;

public class OfficerConverter extends UserConverter{

	public static Officer toEntity(OfficerDto officerDto) {
		if( officerDto == null)
			return null;
		Officer officer = new Officer();
		officer.setAddress(officerDto.getAddress());
		officer.setEmail(officerDto.getEmail());
		officer.setName(officerDto.getName());
		officer.setPassword(officerDto.getPassword());
		officer.setSurname(officerDto.getSurname());
		officer.setUserId(officerDto.getUserId());
		return officer;
	}
	
	public static OfficerDto toDto(Officer officer) {
		if( officer == null)
			return null;
		OfficerDto officerDto = new OfficerDto();
		officerDto.setAddress(officer.getAddress());
		officerDto.setEmail(officer.getEmail());
		officerDto.setName(officer.getName());
		officerDto.setPassword(officer.getPassword());
		officerDto.setSurname(officer.getSurname());
		officerDto.setUserId(officer.getUserId());
		return officerDto;
	}
	
}
