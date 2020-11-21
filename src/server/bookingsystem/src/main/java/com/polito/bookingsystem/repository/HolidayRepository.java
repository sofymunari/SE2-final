package com.polito.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Integer>{
   
  
}
