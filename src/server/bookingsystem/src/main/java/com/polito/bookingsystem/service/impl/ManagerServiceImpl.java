package com.polito.bookingsystem.service.impl;

import com.polito.bookingsystem.converter.ManagerConverter;
import com.polito.bookingsystem.dto.ManagerDto;
import com.polito.bookingsystem.entity.Manager;
import com.polito.bookingsystem.repository.ManagerRepository;
import com.polito.bookingsystem.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	ManagerRepository managerRepository;
	
	
	@Autowired
	public ManagerServiceImpl(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}
	
	
	@Override
	public String login(String email, String password) {
		Manager manager = managerRepository.findByEmail(email);
		
		if(manager != null && manager.getPassword().equals(password)) return email;
			
		return null;
	}


	@Override
	public ManagerDto getManager(String email) {
		Manager manager = managerRepository.findByEmail(email);	
		
		if(manager == null) return null;
		
		return ManagerConverter.toDto(manager);
	}
}
