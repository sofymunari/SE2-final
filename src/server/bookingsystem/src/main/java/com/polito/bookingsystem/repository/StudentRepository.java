package com.polito.bookingsystem.repository;

import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.Student;

@Repository
public interface StudentRepository extends UserRepository{
   Student findByUserId(Integer userId);
   Student findByEmail(String email);
   
}
