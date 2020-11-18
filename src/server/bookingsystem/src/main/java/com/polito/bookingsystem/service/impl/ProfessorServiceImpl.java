package com.polito.bookingsystem.service.impl;


import com.polito.bookingsystem.service.ProfessorService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.entity.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
    ProfessorRepository professorRepository;
	
	
    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository)
    {
    	this.professorRepository = professorRepository;
    }
    
    
	@Override
	public String login(String email, String password) {
		Professor professor = professorRepository.findByEmail(email);

        if(professor != null && professor.getPassword().equals(password)) return email;
        
		return null;
	}
}
