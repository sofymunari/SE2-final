package com.polito.bookingsystem.dto;

import java.util.Date;

public class NotificationDto {
	private Integer notificationId;
	private String description;
	private Date date;
	
	public NotificationDto() {}
	public NotificationDto(Integer notificationId, String description, Date date) {
		super();
		this.notificationId = notificationId;
		this.description = description;
		this.date = date;
	}

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
