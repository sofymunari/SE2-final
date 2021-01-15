package com.polito.bookingsystem.converter;
import com.polito.bookingsystem.dto.NotificationDto;
import com.polito.bookingsystem.entity.Notification;

public class NotificationConverter {
	protected NotificationConverter() {
		
	}
	public static Notification toEntity(NotificationDto notificationDto) {
		if( notificationDto == null)
			return null;
		
		Notification notification = new Notification();
		notification.setNotificationId(notificationDto.getNotificationId());
		notification.setDate(notificationDto.getDate());
		notification.setDescription(notificationDto.getDescription());
		notification.setLink(notificationDto.getLink());
		notification.setStatus(notificationDto.getStatus());
		return notification;
	}
	
	public static NotificationDto toDto(Notification notification) {
		if( notification == null)
			return null;
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setNotificationId(notification.getNotificationId());
		notificationDto.setDate(notification.getDate());
		notificationDto.setDescription(notification.getDescription());
		notificationDto.setLink(notification.getLink());
		notificationDto.setStatus(notification.getStatus());
		return notificationDto;
	}
	
}
