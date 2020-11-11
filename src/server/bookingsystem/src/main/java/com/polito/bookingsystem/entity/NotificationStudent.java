package com.polito.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class NotificationStudent extends Notification{
	
	@ManyToOne
    @JoinColumn(name ="userId")
    private Student student;
   
	public NotificationStudent() {
		super();
	}
	
	public NotificationStudent(Integer notificationId, String description, Date date, Student student) {
		super(notificationId, description, date);
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
    
	
}
