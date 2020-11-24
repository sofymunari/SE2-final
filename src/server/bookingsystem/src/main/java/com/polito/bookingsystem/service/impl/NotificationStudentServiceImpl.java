package com.polito.bookingsystem.service.impl;
import com.polito.bookingsystem.converter.NotificationStudentConverter;
import com.polito.bookingsystem.converter.StudentConverter;
import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.NotificationStudent;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.NotificationStudentRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.NotificationStudentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationStudentServiceImpl implements NotificationStudentService {
	
	@Autowired
	NotificationStudentRepository notificationStudentRepository;
	
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	public NotificationStudentServiceImpl(NotificationStudentRepository notificationStudentRepository, StudentRepository studentRepository) {
		this.notificationStudentRepository = notificationStudentRepository;
		this.studentRepository = studentRepository;
	}
	
	@Override
	public boolean sendStudentNotification(StudentDto studentDto, String description, String link) {
		
		if (studentDto == null) 
			return false;
		
		Student student = StudentConverter.toEntity(studentDto);
		if(studentRepository.findByUserId(studentDto.getUserId()) != null) {
			NotificationStudent notificationStudent= new NotificationStudent();
			notificationStudent.setDate(new Date());
			notificationStudent.setDescription(description);
			notificationStudent.setStudent(student);
			notificationStudent.setLink(link);
			notificationStudent.setStatus(false);
			notificationStudentRepository.save(notificationStudent);
			
			return true;
		}

		return false;
	}

	@Override
	public List<NotificationStudentDto> getStudentNotifications(StudentDto studentDto) {
		if (studentDto == null) 
			return new ArrayList<>();
		
		if(studentRepository.findByUserId(studentDto.getUserId()) != null) {
			List<NotificationStudent> notificationStudentList= notificationStudentRepository.findByStudent(StudentConverter.toEntity(studentDto));
			
			return NotificationStudentConverter.toDto(notificationStudentList);
		}
		return new ArrayList<>();
	}

	@Override
	public boolean setNotificationAsRead(NotificationStudentDto notificationStudentDto) {
		
		if (notificationStudentDto == null) 
			return false;
		
		if(notificationStudentRepository.findByNotificationId(notificationStudentDto.getNotificationId()) != null) {
			notificationStudentDto.setStatus(true);
			NotificationStudent notificationStudent= NotificationStudentConverter.toEntity(notificationStudentDto);
			notificationStudentRepository.save(notificationStudent);
			return true;
		}
		return false;
	}

	@Override
	public NotificationStudentDto getStudentNotificationByNotificationId(Integer notificationId) {
		NotificationStudent notificationStudent = notificationStudentRepository.findByNotificationId(notificationId);
		return NotificationStudentConverter.toDto(notificationStudent);
	}
}
