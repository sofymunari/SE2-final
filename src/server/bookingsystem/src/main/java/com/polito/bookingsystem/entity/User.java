package com.polito.bookingsystem.entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User {
	@Column
	@Id
	private Integer userId;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	public User() {
	}
	
	public User(Integer userId, String name, String surname, String address, String email, String password) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
