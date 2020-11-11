package com.polito.bookingsystem.entity;

import javax.persistence.Entity;

@Entity
public class Officer extends User{

	public Officer() {
		super();
	}

	public Officer(Integer userId, String name, String surname, String address, String email, String password) {
		super(userId, name, surname, address, email, password);
	}
	
}
