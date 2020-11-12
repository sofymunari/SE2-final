package com.polito.bookingsystem.dto;


public class ManagerDto extends UserDto {

	public ManagerDto() {
		super();
	}

	public ManagerDto(Integer userId, String name, String surname, String address, String email, String password) {
		super(userId, name, surname, address, email, password);
	}
	 
}
