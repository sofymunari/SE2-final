package com.polito.bookingsystem.dto;

import java.util.Date;

public class NotificationStudentDto extends NotificationDto{
    private StudentDto studentDto;
    
	public NotificationStudentDto() {
		super();
	}
	
	public NotificationStudentDto(Integer notificationId,String description, Date date, StudentDto studentDto, boolean status, String link) {
		super(notificationId, description, date, status, link);
		this.studentDto = studentDto;
	}

	public StudentDto getStudentDto() {
		return studentDto;
	}

	public void setStudentDto(StudentDto studentDto) {
		this.studentDto = studentDto;
	}
    
	
	
}
