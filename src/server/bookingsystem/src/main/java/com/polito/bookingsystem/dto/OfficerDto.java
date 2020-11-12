package com.polito.bookingsystem.dto;

public class OfficerDto extends UserDto{

	public OfficerDto() {
		super();
	}

	public OfficerDto(Integer userId, String name, String surname, String address, String email, String password) {
		super(userId, name, surname, address, email, password);
	}
	
}
