package com.polito.bookingsystem.repository;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.polito.bookingsystem.entity.User;

@NoRepositoryBean
public interface UserRepository<T extends User, E extends Serializable> extends CrudRepository<T, E>{
   
}
