package com.polito.bookingsystem.utils.serviceTests;

import static org.junit.Assert.*;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.impl.ProfessorServiceImpl;
import com.polito.bookingsystem.service.impl.StudentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RunWith(SpringRunner.class)
@DataJpaTest
class ProfessorServiceTests {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	private ProfessorServiceImpl professorServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		
		professorRepository = mock(ProfessorRepository.class);
		professorServiceImpl = new ProfessorServiceImpl(professorRepository);
	}

	@Test
	void testLoginProfessor1() {
		//passing an invalid mail 
		when(professorRepository.findByEmail(anyString())).thenReturn(null);
		assertNull("Expected a null value returned, invalid email", professorServiceImpl.login("wrong@email.com", "fakePassword"));
	}
	
	@Test
	void testLoginProfessor2() throws ParseException {
		//passing a valid mail but an invalid password
		
		Course course1 = new Course(1, "testName", "testDescription");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses);
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor);

		assertNull("Expected a null value returned, invalid password", professorServiceImpl.login("test@email.com", "wrongPassword"));
	}
	
	@Test
	void testLoginProfessor3() throws ParseException {
		//passing a valid mail and a valid password
		
		Course course1 = new Course(1, "testName", "testDescription");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses);
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor);

		assertEquals("Expected test@email.com to be returned, successful login", professorServiceImpl.login("test@email.com", "testPassword"), "test@email.com");
	}
	
	@Test
	void testLoginProfessor4() {
		//passing a null mail and a null password
		
		when(professorRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned, invalid email and password", professorServiceImpl.login(null, null));
	}

	
	@Test
	void testGetProfessor1() {
		//null email 
		
		assertNull("Expected a null value to be returned, null email", professorServiceImpl.getProfessor(null));
	}
	
	@Test
	void testGetProfessor2() {
		//invalid email 
		
		when(professorRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value to be returned, invalid email", professorServiceImpl.getProfessor("wrong@email.com"));
	}
	
	@Test
	void testGetProfessor3() {
		//invalid email 
		
		Course course1 = new Course(1, "testName", "testDescription");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Professor professor = new Professor(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses);
		
		when(professorRepository.findByEmail(anyString())).thenReturn(professor);
		
		assertEquals("Expected a professor to be returned, valid email", professorServiceImpl.getProfessor("test@email.com").getUserId(), professor.getUserId());
	}
	
	
	

}