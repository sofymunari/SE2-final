package com.polito.bookingsystem.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.polito.bookingsystem.entity.NotificationProfessor;
import com.polito.bookingsystem.entity.NotificationStudent;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Student;

@Repository
public interface NotificationStudentRepository extends NotificationRepository{
	List<NotificationStudent> findByStudent(Student student);
	List<NotificationStudent> findByStudentAndStatus(Student student, boolean status);
	NotificationStudent findByNotificationStudentId(Integer notificationId);
  
}
