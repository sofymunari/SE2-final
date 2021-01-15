package com.polito.bookingsystem.service.impl;
import com.polito.bookingsystem.converter.OfficerConverter;
import com.polito.bookingsystem.dto.OfficerDto;
import com.polito.bookingsystem.entity.Officer;
import com.polito.bookingsystem.repository.OfficerRepository;
import com.polito.bookingsystem.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficerServiceImpl implements OfficerService {
	
	@Autowired
	OfficerRepository officerRepository;
	
	@Autowired
	public OfficerServiceImpl(OfficerRepository officerRepository) {
		this.officerRepository = officerRepository;
	}

	@Override
	public String login(String email, String password) {
		Officer officer = officerRepository.findByEmail(email);
		
		if(officer != null && officer.getPassword().equals(password)) return email;
		
		return null;
	}

	@Override
	public OfficerDto getOfficer(String email) {
		Officer officer = officerRepository.findByEmail(email);
		
		if(officer == null) return null;
		
		return OfficerConverter.toDto(officer);
	}
}
