package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.NotificationProfessor;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.repository.NotificationProfessorRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.service.impl.NotificationProfessorServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class NotificationProfessorServiceTest {
	
	@Autowired
	private NotificationProfessorRepository notificationProfessorRepository;

	@Autowired
	private ProfessorRepository professorRepository;
	
	private NotificationProfessorServiceImpl notificationProfessorServiceImpl;
	

	@BeforeEach
	void setUp() throws Exception {
		notificationProfessorRepository = mock(NotificationProfessorRepository.class);
		professorRepository = mock(ProfessorRepository.class);
		notificationProfessorServiceImpl = new NotificationProfessorServiceImpl(notificationProfessorRepository, professorRepository);
		
	}

	@Test
	void testSendProfessorNotification1()  {
		//passing null professorDto
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses1);
		
		when(professorRepository.findByUserId(anyInt())).thenReturn(professor1);
		
		assertFalse( "Expected false, null professor", notificationProfessorServiceImpl.sendProfessorNotification(null,  "testDescription", "testLink"));

	}

	
	@Test
	void testSendProfessorNotification2() throws ParseException {
		//passing valid professorDto
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses1);
				
		CourseDto courseDto1 = new CourseDto(1, "testName1", "testDescription1");
		CourseDto courseDto2= new CourseDto(2, "testName2", "testDescription2");
		List<CourseDto> courses2 = new ArrayList<>();
		courses2.add(courseDto1);
		courses2.add(courseDto2);

		NotificationProfessor notificationProfessor = new NotificationProfessor();
	
		ProfessorDto professorDto1 = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses2);
		when(professorRepository.findByUserId(anyInt())).thenReturn(professor1);
		when(notificationProfessorRepository.save(anyObject())).thenReturn(notificationProfessor);
		
		assertTrue("Expected true, valid professor", notificationProfessorServiceImpl.sendProfessorNotification(professorDto1,  "testDescription", "testLink"));

	}
	
	@Test
	void testGetProfessorNotifications1() throws ParseException {
		//passing null professorDto
				
		assertTrue("Expected an empty list", notificationProfessorServiceImpl.getProfessorNotifications(null).isEmpty());

	}
	
	@Test
	void testGetProfessorNotifications2() throws ParseException {
		//passing valid professorDto
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses1);
		
		CourseDto courseDto1 = new CourseDto(1, "testName1", "testDescription1");
		CourseDto courseDto2= new CourseDto(2, "testName2", "testDescription2");
		List<CourseDto> courses2 = new ArrayList<>();
		courses2.add(courseDto1);
		courses2.add(courseDto2);

		NotificationProfessor notificationProfessor1 = new NotificationProfessor(1, "testDescription1", date, professor1, true, "testLink1" );
		NotificationProfessor notificationProfessor2 = new NotificationProfessor(2, "testDescription2", date, professor1, true, "testLink2" );
		
		List<NotificationProfessor> list = new ArrayList<>();
		list.add(notificationProfessor1);
		list.add(notificationProfessor2);

		ProfessorDto professorDto1 = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses2);

		when(professorRepository.findByUserId(anyInt())).thenReturn(professor1);
		when(notificationProfessorRepository.findByProfessor(anyObject())).thenReturn(list);
				
		assertTrue("Expected a list with 2 elements", notificationProfessorServiceImpl.getProfessorNotifications(professorDto1).size() == 2);

	}
	
	@Test
	void testSetNotificationAsRead1() {
		//passing null NotificationProfessortDto
		
		assertFalse("Expected false, null parameter", notificationProfessorServiceImpl.setNotificationAsRead(null));
	}
	
	@Test
	void testSetNotificationAsRead2() throws ParseException {
		//passing valid NotificationProfessorDto
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses1);
		
		CourseDto courseDto1 = new CourseDto(1, "testName1", "testDescription1");
		CourseDto courseDto2= new CourseDto(2, "testName2", "testDescription2");
		List<CourseDto> courses2 = new ArrayList<>();
		courses2.add(courseDto1);
		courses2.add(courseDto2);
		
		ProfessorDto professorDto1 = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses2);

		
		NotificationProfessorDto notificationProfessorDto = new NotificationProfessorDto(1, "testDescription1", date,  professorDto1, true, "testLink1");
		NotificationProfessor notificationProfessor = new NotificationProfessor(1, "testDescription1", date,  professor1, true, "testLink1" );

		
		when(notificationProfessorRepository.findByNotificationId(anyInt())).thenReturn(notificationProfessor);
		when(notificationProfessorRepository.save(anyObject())).thenReturn(null);
		
		assertTrue("Expected true", notificationProfessorServiceImpl.setNotificationAsRead(notificationProfessorDto));
	}
	
}
