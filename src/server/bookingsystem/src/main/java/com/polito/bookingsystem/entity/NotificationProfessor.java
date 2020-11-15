package com.polito.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class NotificationProfessor extends Notification{
	
	@ManyToOne
    @JoinColumn(name ="userId")
	private Professor professor;
	
	public NotificationProfessor() {
		super();
	}
	
	public NotificationProfessor(Integer notificationId, String description, Date date, Professor professor, boolean status, String link) {
		super(notificationId, description, date, status, link);
		this.professor = professor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
   
	
}
