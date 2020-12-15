package com.polito.bookingsystem.service;

import com.polito.bookingsystem.dto.OfficerDto;

public interface OfficerService {
	String login(String email, String password);
	OfficerDto getOfficer(String email);
}
