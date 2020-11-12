package com.polito.officequeue.repository;
import java.util.List;
import  java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polito.officequeue.entity.Counter;
import com.polito.officequeue.entity.Request;

public interface CounterRepository extends JpaRepository<Counter,Integer>{
   public Optional<Counter> findById(Integer counter_id);
   public List<Request> findByCounterId(Integer counter_id);
  
}
