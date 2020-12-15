package com.polito.bookingsystem.repository;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Student;

@Repository
public interface StudentRepository extends UserRepository<Student, Integer>{
   Student findByUserId(Integer userId);
   Student findByEmail(String email);
   Student findByMatricola(String matricola);
   List<Student> findAll();
}
