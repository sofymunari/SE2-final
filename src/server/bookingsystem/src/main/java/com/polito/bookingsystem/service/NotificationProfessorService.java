package com.polito.bookingsystem.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.dto.ProfessorDto;

@Service
public interface NotificationProfessorService {
	public boolean sendProfessorNotification(ProfessorDto professorDto, String description, String link);
	public List<NotificationProfessorDto> getProfessorNotifications(ProfessorDto professorDto);
	public boolean setNotificationAsRead(NotificationProfessorDto notificationProfessorDto);
	public NotificationProfessorDto getProfessorNotificationByNotificationId(Integer notificationId);
}
