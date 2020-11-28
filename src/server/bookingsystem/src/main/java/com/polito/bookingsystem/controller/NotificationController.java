package com.polito.bookingsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.NotificationProfessorService;
import com.polito.bookingsystem.service.NotificationStudentService;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.StudentService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public class NotificationController {
	@Autowired
	NotificationStudentService notificationStudentService;
	
	@Autowired
	StudentService studentService;

	@Autowired
	NotificationProfessorService notificationProfessorService;
	
	@Autowired
	ProfessorService professorService;

	 @GetMapping("/student/notification/list/{studentId}")
	 public List<NotificationStudentDto> getStudentNotifications(@PathVariable String email) {
	
		 StudentDto studentDto = studentService.getStudent(email);
		 return notificationStudentService.getStudentNotifications(studentDto);
	 }
	 
	 @GetMapping("/student/notification/{notificationId}")
	 public boolean setNotificationAsRead(@PathVariable Integer notificationId) {
		
		 NotificationStudentDto notificationStudentDto = notificationStudentService.getStudentNotificationByNotificationId(notificationId);
		return notificationStudentService.setNotificationAsRead(notificationStudentDto );
	 }

	 @GetMapping("/professor/notification/list/{email}")
	 public List<NotificationProfessorDto> getProfessorNotifications(@PathVariable String email) {
	
		ProfessorDto professorDto = professorService.getProfessor(email);
		return notificationProfessorService.getProfessorNotifications(professorDto);
	 }
	 
	 @GetMapping("/professor/notification/{notificationId}")
	 public boolean getProfessortNotification(@PathVariable Integer notificationId) {
			
		 NotificationProfessorDto notificationProfessorDto = notificationProfessorService.getProfessorNotificationByNotificationId(notificationId);
		 return notificationProfessorService.setNotificationAsRead(notificationProfessorDto);
	 }
}
