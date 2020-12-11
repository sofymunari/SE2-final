package com.polito.bookingsystem.repository;
import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Manager;


@Repository
public interface ManagerRepository extends UserRepository<Manager, Integer>{
	Manager findByUserId(Integer userId);
	Manager findByEmail(String email);
}
