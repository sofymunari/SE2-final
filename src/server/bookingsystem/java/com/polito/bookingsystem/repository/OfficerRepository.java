package com.polito.bookingsystem.repository;
import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Officer;

@Repository
public interface OfficerRepository extends UserRepository<Officer, Integer>{
	Officer findByUserId(Integer userId);
	Officer findByEmail(String email);
}
