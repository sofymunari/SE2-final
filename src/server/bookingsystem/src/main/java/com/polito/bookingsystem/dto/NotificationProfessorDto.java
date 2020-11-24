package com.polito.bookingsystem.dto;
import java.util.Date;

public class NotificationProfessorDto extends NotificationDto{
    private ProfessorDto professorDto;
	public NotificationProfessorDto() {
		super();
	}
	public NotificationProfessorDto(Integer notificationId, String description, Date date, ProfessorDto professorDto, boolean status, String link) {
		super(notificationId, description, date, status, link);
		this.professorDto = professorDto;
	}
	public ProfessorDto getProfessorDto() {
		return professorDto;
	}
	public void setProfessorDto(ProfessorDto professorDto) {
		this.professorDto = professorDto;
	}
}
