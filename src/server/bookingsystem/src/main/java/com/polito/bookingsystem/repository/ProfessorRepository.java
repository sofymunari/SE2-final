package com.polito.bookingsystem.repository;

import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Professor;

@Repository
public interface ProfessorRepository extends UserRepository{
   
	Professor findByUserId(Integer id);
	Professor findByEmail(String email);
}
