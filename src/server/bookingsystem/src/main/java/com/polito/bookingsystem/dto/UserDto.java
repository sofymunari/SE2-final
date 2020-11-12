package com.polito.bookingsystem.dto;


public abstract class UserDto {
	private Integer userId;
	private String name;
	private String surname;
	private String address;
	private String email;
	private String password;
	
	public UserDto() {
	}
	
	public UserDto(Integer userId, String name, String surname, String address, String email, String password) {
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

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", name=" + name + ", surname=" + surname + ", address="
				+ address + ", email=" + email + ", password=" + password +"]";
	}
	
	
}
