package com.polito.bookingsystem.converter;

import com.polito.bookingsystem.dto.NotificationDto;
import com.polito.bookingsystem.entity.Notification;

public abstract class NotificationConverter {
	
	public static Notification toEntity(NotificationDto notificationDto) {
		Notification notification = new Notification(notificationDto.getNotificationId(), notificationDto.getDescription(), notificationDto.getDate(), notificationDto.getStatus(), notificationDto.getLink());
		return notification;
	}
	
	public static NotificationDto toDto(Notification notification) {
		NotificationDto notificationDto = new NotificationDto(notification.getNotificationId(), notification.getDescription(), notification.getDate(), notification.getStatus(), notification.getLink());
		return notificationDto;
	}
	
}
