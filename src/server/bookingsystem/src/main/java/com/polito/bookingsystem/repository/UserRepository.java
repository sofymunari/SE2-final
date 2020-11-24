package com.polito.bookingsystem.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.polito.bookingsystem.entity.User;

@NoRepositoryBean
public interface UserRepository extends CrudRepository<User,Integer>{
   
  
}
