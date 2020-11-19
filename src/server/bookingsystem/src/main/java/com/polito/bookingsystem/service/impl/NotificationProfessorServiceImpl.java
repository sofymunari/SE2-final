package com.polito.bookingsystem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polito.bookingsystem.converter.NotificationProfessorConverter;
import com.polito.bookingsystem.converter.NotificationStudentConverter;
import com.polito.bookingsystem.converter.ProfessorConverter;
import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.entity.NotificationProfessor;
import com.polito.bookingsystem.entity.NotificationStudent;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.repository.NotificationProfessorRepository;
import com.polito.bookingsystem.repository.NotificationStudentRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.NotificationProfessorService;

@Service
public class NotificationProfessorServiceImpl implements NotificationProfessorService {
	@Autowired
	NotificationProfessorRepository notificationProfessorRepository;
	
	@Autowired
	ProfessorRepository professorRepository;


	@Autowired
	public NotificationProfessorServiceImpl(NotificationProfessorRepository notificationProfessorRepository, ProfessorRepository professorRepository) {
		this.notificationProfessorRepository = notificationProfessorRepository;
		this.professorRepository = professorRepository;
	}
	
	
	@Override
	public boolean sendProfessorNotification(ProfessorDto professorDto, String description, String link) {
		if (professorDto == null) 
			return false;
		Professor professor = ProfessorConverter.toEntity(professorDto);
		if(professorRepository.findByUserId(professorDto.getUserId()) != null) {
			NotificationProfessor notificationProfessor= new NotificationProfessor();
			notificationProfessor.setDate(new Date());
			notificationProfessor.setDescription(description);
			notificationProfessor.setProfessor(professor);
			notificationProfessor.setLink(link);
			notificationProfessor.setStatus(false);
			if(notificationProfessorRepository.save(notificationProfessor) != null) {
				return true;
			}
		}

		return false;
	}

	@Override
	public List<NotificationProfessorDto> getProfessorNotifications(ProfessorDto professorDto) {
		if (professorDto == null) 
			return new ArrayList<>();
		if(professorRepository.findByUserId(professorDto.getUserId()) != null) {
			List<NotificationProfessor> notificationProfessorList= notificationProfessorRepository.findByProfessor(ProfessorConverter.toEntity(professorDto));
			
			return NotificationProfessorConverter.toDto(notificationProfessorList);
		}
		return null;
	}

	@Override
	public boolean setNotificationAsRead(NotificationProfessorDto notificationProfessorDto) {
		
		if (notificationProfessorDto == null) 
			return false;
		
		if(notificationProfessorRepository.findByNotificationId(notificationProfessorDto.getNotificationId()) != null) {
			notificationProfessorDto.setStatus(true);
			NotificationProfessor notificationProfessor = NotificationProfessorConverter.toEntity(notificationProfessorDto);
			notificationProfessorRepository.save(notificationProfessor);
			return true;
		}
		return false;
	}


	@Override
	public NotificationProfessorDto getProfessorNotificationByNotificationId(Integer Id) {
		NotificationProfessor notificationProfessor = notificationProfessorRepository.findByNotificationId(Id);
		return NotificationProfessorConverter.toDto(notificationProfessor);
	}

}
