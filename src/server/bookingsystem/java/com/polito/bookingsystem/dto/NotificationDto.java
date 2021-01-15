package com.polito.bookingsystem.dto;
import java.util.Date;

public class NotificationDto {
	private Integer notificationId;
	private String description;
	private Date date;
	private boolean status;
	private String link;
	public NotificationDto() {}
	public NotificationDto(Integer notificationId, String description, Date date, boolean status, String link) {
		super();
		this.notificationId = notificationId;
		this.description = description;
		this.date = date;
		this.status = status;
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status= status;
	}
}
