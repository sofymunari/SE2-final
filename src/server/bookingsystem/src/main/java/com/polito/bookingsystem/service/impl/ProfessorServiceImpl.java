package com.polito.bookingsystem.service.impl;


import com.polito.bookingsystem.service.ProfessorService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.converter.ProfessorConverter;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.dto.ProfessorDto;

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
	
	@Override
	public ProfessorDto getProfessor(String email) {
		Professor professor = professorRepository.findByEmail(email);
		if(professor != null)
		   return ProfessorConverter.toDto(professor);
		return null;
	}
}
