package com.polito.bookingsystem.utils;
import java.text.ParseException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.polito.bookingsystem.service.ProfessorService;

@Component
public class ScheduledTasks {

	@Autowired
	ProfessorService professorService;

	@Scheduled(cron = "0 00 23 * * ?")
	@Transactional
	public void reportCurrentTime() throws ParseException {
		professorService.notifyProfessorsAboutNumberOfStudents();
	}

}
