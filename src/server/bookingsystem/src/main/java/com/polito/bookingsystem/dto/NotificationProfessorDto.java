package com.polito.bookingsystem.dto;

import java.util.Date;

public class NotificationProfessorDto extends NotificationDto{
    private ProfessorDto professorDto;
    
	public NotificationProfessorDto() {
		super();
	}
	
	public NotificationProfessorDto(Integer notificationId, String description, Date date, ProfessorDto professorDto) {
		super(notificationId, description, date);
		this.professorDto = professorDto;
	}

	public ProfessorDto getProfessorDto() {
		return professorDto;
	}

	public void setProfessorDto(ProfessorDto professorDto) {
		this.professorDto = professorDto;
	}
    
	
	
}
