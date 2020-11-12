package com.polito.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Integer>{
   
  
}
