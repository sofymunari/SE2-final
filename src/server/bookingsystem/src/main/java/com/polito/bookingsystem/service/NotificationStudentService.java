package com.polito.bookingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Student;

@Service
public interface NotificationStudentService {
	
	public boolean sendStudentNotification(StudentDto studentDto, String description, String link);
	public List<NotificationStudentDto> getStudentNotifications(StudentDto studentDto);
	public boolean setNotificationAsRead(NotificationStudentDto notificationStudentDto);
}
