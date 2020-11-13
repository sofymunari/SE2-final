package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.entity.NotificationStudent;

public class NotificationStudentConverter extends NotificationConverter{
	
	public static NotificationStudent toEntity(NotificationStudentDto notificationStudentDto) {
		NotificationStudent notificationStudent = new NotificationStudent();
		notificationStudent.setNotificationId(notificationStudentDto.getNotificationId());
		notificationStudent.setDate(notificationStudentDto.getDate());
		notificationStudent.setDescription(notificationStudentDto.getDescription());
		notificationStudent.setStudent(StudentConverter.toEntity(notificationStudentDto.getStudentDto()));
		return notificationStudent;
	}
	
	public static NotificationStudentDto toDto(NotificationStudent notificationStudent) {
		NotificationStudentDto notificationStudentDto = new NotificationStudentDto();
		notificationStudentDto.setNotificationId(notificationStudent.getNotificationId());
		notificationStudentDto.setDate(notificationStudent.getDate());
		notificationStudentDto.setDescription(notificationStudent.getDescription());
		notificationStudentDto.setStudentDto(StudentConverter.toDto(notificationStudent.getStudent()));
		return notificationStudentDto;
	}
    
	
	
}
