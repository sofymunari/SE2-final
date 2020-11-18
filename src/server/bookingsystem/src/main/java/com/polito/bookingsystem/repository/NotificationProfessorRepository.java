package com.polito.bookingsystem.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.NotificationProfessor;
import com.polito.bookingsystem.entity.NotificationStudent;
import com.polito.bookingsystem.entity.Professor;

@Repository
public interface NotificationProfessorRepository extends NotificationRepository{
   
	List<NotificationProfessor> findByProfessor(Professor professor);
	List<NotificationProfessor> findByProfessorAndStatus(Professor professor, boolean status);
	NotificationProfessor findByNotificationId(Integer id);
	NotificationProfessor findByNotificationStudentId(Integer id);

}
