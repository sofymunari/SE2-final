package com.polito.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.Booking;

@Repository
public interface ClassRepository extends JpaRepository<Class,Integer>{
   
  
}
