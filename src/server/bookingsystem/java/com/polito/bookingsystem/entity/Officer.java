package com.polito.bookingsystem.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Officer extends User{

	public Officer() {
		super();
	}
	public Officer(Integer userId, String name, String surname, String address, String email, String password) {
		super(userId, name, surname, address, email, password);
	}
}
