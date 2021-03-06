package com.polito.bookingsystem.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.dto.StudentDto;

@Service
public interface NotificationStudentService {
	
	public boolean sendStudentNotification(StudentDto studentDto, String description, String link);
	public List<NotificationStudentDto> getStudentNotifications(StudentDto studentDto);
	public NotificationStudentDto getStudentNotificationByNotificationId(Integer notificationId);
	public boolean setNotificationAsRead(NotificationStudentDto notificationStudentDto);
}
