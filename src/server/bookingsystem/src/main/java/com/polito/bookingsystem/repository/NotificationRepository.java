package com.polito.bookingsystem.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.polito.bookingsystem.entity.Notification;

@NoRepositoryBean
public interface NotificationRepository extends CrudRepository<Notification,Integer>{
   
  
}
