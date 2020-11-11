package com.polito.bookingsystem.entity;

import javax.persistence.Entity;

@Entity
public class Manager extends User {

	public Manager() {
		super();
	}

	public Manager(Integer userId, String name, String surname, String address, String email, String password) {
		super(userId, name, surname, address, email, password);
	}
	 
}
