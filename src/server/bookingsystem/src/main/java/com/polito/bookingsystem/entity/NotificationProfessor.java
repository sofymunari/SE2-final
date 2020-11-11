package com.polito.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NotificationProfessor extends Notification{
	
	@ManyToOne
    @JoinColumn(name ="userId")
	private Professor professor;
	
	public NotificationProfessor() {
		super();
	}
	
	public NotificationProfessor(Integer notificationId, String description, Date date, Professor professor) {
		super(notificationId, description, date);
		this.professor = professor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
   
	
}
