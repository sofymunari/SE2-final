package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.ManagerDto;
import com.polito.bookingsystem.entity.Manager;

public class ManagerConverter extends UserConverter {

	public static Manager toEntity(ManagerDto managerDto) {
		if( managerDto == null)
			return null;
		Manager manager = new Manager();
		manager.setAddress(managerDto.getAddress());
		manager.setEmail(managerDto.getEmail());
		manager.setName(managerDto.getName());
		manager.setPassword(managerDto.getPassword());
		manager.setSurname(managerDto.getSurname());
		manager.setUserId(managerDto.getUserId());
		return manager;
	}
	
	public static ManagerDto toDto(Manager manager) {
		if( manager == null)
			return null;
		ManagerDto managerDto = new ManagerDto();
		managerDto.setAddress(manager.getAddress());
		managerDto.setEmail(manager.getEmail());
		managerDto.setName(manager.getName());
		managerDto.setPassword(manager.getPassword());
		managerDto.setSurname(manager.getSurname());
		managerDto.setUserId(manager.getUserId());
		return managerDto;
	}
	 
}
