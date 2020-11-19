package com.polito.bookingsystem.utils.serviceTests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
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
import com.polito.bookingsystem.dto.NotificationStudentDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.NotificationStudent;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.NotificationStudentRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.impl.NotificationStudentServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class NotificationStudentServiceTest {
	
	@Autowired
	private NotificationStudentRepository notificationStudentRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	
	private NotificationStudentServiceImpl notificationStudentServiceImpl;
	

	@BeforeEach
	void setUp() throws Exception {
		notificationStudentRepository = mock(NotificationStudentRepository.class);
		studentRepository = mock(StudentRepository.class);
		notificationStudentServiceImpl = new NotificationStudentServiceImpl(notificationStudentRepository, studentRepository);
		
	}

	@Test
	void testSendStudentNotification1() throws ParseException {
		//passing null studentDto
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1);
		
		when(studentRepository.findByUserId(anyInt())).thenReturn(student1);
		
		assertFalse("Expected false, null student", notificationStudentServiceImpl.sendStudentNotification(null,  "testDescription", "testLink"));

	}

	
	@Test
	void testSendStudentNotification2() throws ParseException {
		//passing valid studentDto
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1);
				
		CourseDto courseDto1 = new CourseDto(1, "testName1", "testDescription1");
		CourseDto courseDto2= new CourseDto(2, "testName2", "testDescription2");
		List<CourseDto> courses2 = new ArrayList<>();
		courses2.add(courseDto1);
		courses2.add(courseDto2);

		NotificationStudent notificationStudent = new NotificationStudent();
	
		StudentDto studentDto1 = new StudentDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses2);
		when(studentRepository.findByUserId(anyInt())).thenReturn(student1);
		when(notificationStudentRepository.save(anyObject())).thenReturn(notificationStudent);
		
		assertTrue( "Expected true, valid student", notificationStudentServiceImpl.sendStudentNotification(studentDto1,  "testDescription", "testLink"));

	}
	
	
	@Test
	void testGetStudentNotifications1() throws ParseException {
		//passing null studentDto
				
		assertTrue(notificationStudentServiceImpl.getStudentNotifications(null).isEmpty(), "Expected an empty list");

	}
	
	@Test
	void testGetStudentNotifications2() throws ParseException {
		//passing valid studentDto
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1);
		
		CourseDto courseDto1 = new CourseDto(1, "testName1", "testDescription1");
		CourseDto courseDto2= new CourseDto(2, "testName2", "testDescription2");
		List<CourseDto> courses2 = new ArrayList<>();
		courses2.add(courseDto1);
		courses2.add(courseDto2);

		NotificationStudent notificationStudent1 = new NotificationStudent(1, "testDescription1", date, student1, true, "testLink1" );
		NotificationStudent notificationStudent2 = new NotificationStudent(2, "testDescription2", date, student1, true, "testLink2" );
		
		List<NotificationStudent> list = new ArrayList<>();
		list.add(notificationStudent1);
		list.add(notificationStudent2);

		StudentDto studentDto1 = new StudentDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses2);

		when(studentRepository.findByUserId(anyInt())).thenReturn(student1);
		when(notificationStudentRepository.findByStudent(anyObject())).thenReturn(list);
				
		assertTrue("Expected a list with 2 elements", notificationStudentServiceImpl.getStudentNotifications(studentDto1).size() == 2);

	}
	
	@Test
	void testSetNotificationAsRead1() {
		//passing null NotificationStudentDto
		
		
		assertFalse( "Expected false, null parameter", notificationStudentServiceImpl.setNotificationAsRead(null));
	}
	
	@Test
	void testSetNotificationAsRead2() throws ParseException {
		//passing valid NotificationStudentDto
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);
		
		Student student1 = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses1);
		
		CourseDto courseDto1 = new CourseDto(1, "testName1", "testDescription1");
		CourseDto courseDto2= new CourseDto(2, "testName2", "testDescription2");
		List<CourseDto> courses2 = new ArrayList<>();
		courses2.add(courseDto1);
		courses2.add(courseDto2);
		
		StudentDto studentDto1 = new StudentDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses2);

		
		NotificationStudentDto notificationStudentDto = new NotificationStudentDto(1, "testDescription1", date, studentDto1, true, "testLink1");
		NotificationStudent notificationStudent = new NotificationStudent(1, "testDescription1", date, student1, true, "testLink1" );

		
		when(notificationStudentRepository.findByNotificationId(anyInt())).thenReturn(notificationStudent);
		when(notificationStudentRepository.save(anyObject())).thenReturn(null);
		
		assertTrue("Expected true", notificationStudentServiceImpl.setNotificationAsRead(notificationStudentDto));
	}
	
		
}
