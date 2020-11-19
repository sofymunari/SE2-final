package com.polito.bookingsystem.converter;

import java.util.ArrayList;
import java.util.List;

import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.entity.NotificationProfessor;


public class NotificationProfessorConverter extends NotificationConverter{
	
	public static NotificationProfessor toEntity(NotificationProfessorDto notificationProfessorDto) {
		if( notificationProfessorDto == null)
			return null;
		NotificationProfessor notificationProfessor = new NotificationProfessor();
		notificationProfessor.setNotificationId(notificationProfessorDto.getNotificationId());
		notificationProfessor.setDate(notificationProfessorDto.getDate());
		notificationProfessor.setDescription(notificationProfessorDto.getDescription());
		notificationProfessor.setProfessor(ProfessorConverter.toEntity(notificationProfessorDto.getProfessorDto()));
		notificationProfessor.setStatus(notificationProfessorDto.getStatus());
		notificationProfessor.setLink(notificationProfessorDto.getLink());
		return notificationProfessor;
	}
	
	public static NotificationProfessorDto toDto(NotificationProfessor notificationProfessor) {
		if( notificationProfessor == null)
			return null;
		NotificationProfessorDto notificationProfessorDto = new NotificationProfessorDto();
		notificationProfessorDto.setNotificationId(notificationProfessor.getNotificationId());
		notificationProfessorDto.setDate(notificationProfessor.getDate());
		notificationProfessorDto.setDescription(notificationProfessor.getDescription());
		notificationProfessorDto.setProfessorDto(ProfessorConverter.toDto(notificationProfessor.getProfessor()));
		notificationProfessorDto.setStatus(notificationProfessor.getStatus());
		notificationProfessorDto.setLink(notificationProfessor.getLink());
		return notificationProfessorDto;
	}
	
	public static List<NotificationProfessorDto> toDto(List<NotificationProfessor> notificationProfessorList) {
		if( notificationProfessorList == null)
			return null;
		List<NotificationProfessorDto> notificationProfessorDtoList = new ArrayList<NotificationProfessorDto>(); 
		for (NotificationProfessor notificationProfessor: notificationProfessorList) {
			notificationProfessorDtoList.add(toDto(notificationProfessor));
		}
		return notificationProfessorDtoList;
	}
	
	
}
