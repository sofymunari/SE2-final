package com.polito.bookingsystem.service;

import com.polito.bookingsystem.dto.ManagerDto;

public interface ManagerService {
	public String login(String email, String password);
	public ManagerDto getManager(String email);
}
