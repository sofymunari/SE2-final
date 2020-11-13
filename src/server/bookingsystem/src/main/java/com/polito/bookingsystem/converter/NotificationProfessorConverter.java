package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.entity.NotificationProfessor;

public class NotificationProfessorConverter extends NotificationConverter{
	
	public static NotificationProfessor toEntity(NotificationProfessorDto notificationProfessorDto) {
		NotificationProfessor notificationProfessor = new NotificationProfessor();
		notificationProfessor.setNotificationId(notificationProfessorDto.getNotificationId());
		notificationProfessor.setDate(notificationProfessorDto.getDate());
		notificationProfessor.setDescription(notificationProfessorDto.getDescription());
		notificationProfessor.setProfessor(ProfessorConverter.toEntity(notificationProfessorDto.getProfessorDto()));
		return notificationProfessor;
	}
	
	public static NotificationProfessorDto toDto(NotificationProfessor notificationProfessor) {
		NotificationProfessorDto notificationProfessorDto = new NotificationProfessorDto();
		notificationProfessorDto.setNotificationId(notificationProfessor.getNotificationId());
		notificationProfessorDto.setDate(notificationProfessor.getDate());
		notificationProfessorDto.setDescription(notificationProfessor.getDescription());
		notificationProfessorDto.setProfessorDto(ProfessorConverter.toDto(notificationProfessor.getProfessor()));
		return notificationProfessorDto;
	}
	
	
}
