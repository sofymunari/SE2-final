package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.impl.StudentServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class StudentServiceTests {

	@Autowired
	private StudentRepository studentRepository;	

	private JavaMailSender javaMailSender;
	
	private StudentServiceImpl studentServiceImpl;
	
	
	@BeforeEach
	public void setUp() throws Exception {

		studentRepository = mock(StudentRepository.class);
		javaMailSender = mock( JavaMailSender.class);
		studentServiceImpl = new StudentServiceImpl(studentRepository, javaMailSender);
	}


	@Test
	void testGetStudent1() {
		//passing an invalid mail 
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value returned, invalid mail", studentServiceImpl.getStudent("wrong@email.com"));
	}
	
	@Test
	void testGetStudent2() throws ParseException {
		//passing a valid mail 
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Course course1 = new Course(1, "testName", "testDescription");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		
		when(studentRepository.findByEmail(anyString())).thenReturn(student);

		assertTrue("The student returned is not the expected one", studentServiceImpl.getStudent("test@email.com").getUserId() == 1);
	}
	
	@Test
	void testGetStudent3() {
		//passing a null mail 
		
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value to be returned", studentServiceImpl.getStudent(null));
	}
	
	@Test
	void testLoginStudent1() {
		//passing an invalid mail 
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value to be returned, invalid email", studentServiceImpl.login("wrong@email.com", "fakePassword"));
	}
	
	@Test
	void testLoginStudent2() throws ParseException {
		//passing a valid mail but an invalid password
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Course course1 = new Course(1, "testName", "testDescription");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		
		when(studentRepository.findByEmail(anyString())).thenReturn(student);

		assertNull("Expected a null value to be returned, invalid password", studentServiceImpl.login("test@email.com", "wrongPassword"));
	}
	
	@Test
	void testLoginStudent3() throws ParseException {
		//passing a valid mail and a valid password
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		Course course1 = new Course(1, "testName", "testDescription");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		
		when(studentRepository.findByEmail(anyString())).thenReturn(student);

		assertEquals("Expected test@email.com to be returned, successful login", studentServiceImpl.login("test@email.com", "testPassword"), "test@email.com");
	}
	
	@Test
	void testLoginStudent4() {
		//passing a null mail and a null password
		
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned, invalid email and password", studentServiceImpl.login(null, null));
	}
	
	@Test
	public void testSendEmail1(){
	   try{
	      studentServiceImpl.sendEmail(null, "test", "test");
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	@Test
	public void testSendEmail2(){
	   try{
			SimpleMailMessage msg = new SimpleMailMessage();

		   Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		   CourseDto course1 = new CourseDto(1, "testName", "testDescription");
		   List<CourseDto> courses = new ArrayList<>();
		   courses.add(course1);
		   StudentDto studentDto = new StudentDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		   doNothing().when(javaMailSender).send(msg);
		   studentServiceImpl.sendEmail(studentDto, "test", "test");
	  
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}

}