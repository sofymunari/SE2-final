package com.polito.bookingsystem.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Notification {
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer notificationId;
    
	@Column
	private String description;
	
	@Column
	private Date date;
	
	@Column
	private boolean status;
	
	@Column
	private String link;
	
	public Notification() {}
	
	public Notification(Integer notificationId,  String description, Date date, boolean status, String link) {
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
		this.status = status;
	}
	
}
