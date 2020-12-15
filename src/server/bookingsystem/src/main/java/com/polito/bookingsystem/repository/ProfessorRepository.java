package com.polito.bookingsystem.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.Professor;

@Repository
public interface ProfessorRepository extends UserRepository<Professor, Integer>{
	Professor findByUserId(Integer id);
	Professor findByEmail(String email);
	Professor findByCode(String code);
	List<Professor> findAll();
}
