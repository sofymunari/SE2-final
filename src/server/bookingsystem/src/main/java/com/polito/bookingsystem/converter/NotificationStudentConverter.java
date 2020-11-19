package com.polito.bookingsystem.converter;

import java.util.ArrayList;
import java.util.List;

import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.entity.NotificationStudent;

public class NotificationStudentConverter extends NotificationConverter{
	
	public static NotificationStudent toEntity(NotificationStudentDto notificationStudentDto) {
		if( notificationStudentDto == null)
			return null;
		NotificationStudent notificationStudent = new NotificationStudent();
		notificationStudent.setNotificationId(notificationStudentDto.getNotificationId());
		notificationStudent.setDate(notificationStudentDto.getDate());
		notificationStudent.setDescription(notificationStudentDto.getDescription());
		notificationStudent.setStudent(StudentConverter.toEntity(notificationStudentDto.getStudentDto()));
		notificationStudent.setStatus(notificationStudentDto.getStatus());
		notificationStudent.setLink(notificationStudentDto.getLink());


		return notificationStudent;
	}
	
	public static NotificationStudentDto toDto(NotificationStudent notificationStudent) {
		if( notificationStudent == null)
			return null;
		NotificationStudentDto notificationStudentDto = new NotificationStudentDto();
		notificationStudentDto.setNotificationId(notificationStudent.getNotificationId());
		notificationStudentDto.setDate(notificationStudent.getDate());
		notificationStudentDto.setDescription(notificationStudent.getDescription());
		notificationStudentDto.setStudentDto(StudentConverter.toDto(notificationStudent.getStudent()));
		notificationStudentDto.setStatus(notificationStudent.getStatus());
		notificationStudentDto.setLink(notificationStudent.getLink());


		return notificationStudentDto;
	}
    
	public static List<NotificationStudentDto> toDto(List<NotificationStudent> notificationStudentList) {
		if( notificationStudentList == null)
			return null;
		List<NotificationStudentDto> notificationStudentDtoList = new ArrayList<NotificationStudentDto>(); 
		for (NotificationStudent notificationStudent: notificationStudentList) {
			notificationStudentDtoList.add(toDto(notificationStudent));
		}
		return notificationStudentDtoList;
	}
	
	
}
