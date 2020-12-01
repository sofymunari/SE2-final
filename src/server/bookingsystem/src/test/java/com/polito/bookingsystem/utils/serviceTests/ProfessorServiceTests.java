package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.polito.bookingsystem.dto.CourseDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Room;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.BookingRepository;
import com.polito.bookingsystem.repository.LectureRepository;
import com.polito.bookingsystem.repository.ProfessorRepository;
import com.polito.bookingsystem.service.impl.ProfessorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProfessorServiceTests {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	private ProfessorServiceImpl professorServiceImpl;

	private JavaMailSender javaMailSender;

	@BeforeEach
	void setUp() throws Exception {
		
		professorRepository = mock(ProfessorRepository.class);
		lectureRepository = mock(LectureRepository.class);
		bookingRepository = mock(BookingRepository.class);
		javaMailSender = mock( JavaMailSender.class);
		professorServiceImpl = new ProfessorServiceImpl(professorRepository, javaMailSender, lectureRepository, bookingRepository);
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
	
	
	@Test 
	void testGetNextDate1() throws ParseException {
		//null string
		
		assertNull("Expected a null value", professorServiceImpl.getNextDate(null));
	}
	
	@Test 
	void testGetNextDate2() {
		//invalid string
		
		assertThrows(ParseException.class, ()-> professorServiceImpl.getNextDate("this could not be parsed"), "Expected a parse exception to be thrown");
	}
	
	
	@Test 
	void testGetNextDate3() throws ParseException {
		//null string
		
		String date = "2020-12-12";
		
		assertTrue("Expected a null value", professorServiceImpl.getNextDate("2020-12-11").equals(date));
	}
	
	@Test
	public void testSendEmail1(){
	   try{
		   professorServiceImpl.sendEmail(null, "test", "test");
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
			ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", courses);		 
			doNothing().when(javaMailSender).send(msg);
			professorServiceImpl.sendEmail(professorDto, "test", "test");
	  
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	
	
	@Test
	public void testNotifyProfessorsAboutNumberOfStudents() throws ParseException {
		
		Room room1 = new Room(1, "testName", 100);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("02/12/2020");
		
		Course course1 = new Course(1, "testName1", "testDescription1");
		Course course2 = new Course(2, "testName2", "testDescription2");
		Course course3 = new Course(3, "testName3", "testDescription3");
		List<Course> courses1 = new ArrayList<>();
		courses1.add(course1);
		courses1.add(course2);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course1);
		courses2.add(course2);
		courses2.add(course3);
		
		Professor professor1 = new Professor(1, "testName", "testSurname", "testAddress", "testProfessor@email.com", "testPassword",courses2);

		Lecture lecture1 = new Lecture(1, 10, course1, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture2 = new Lecture(2, 10, course2, professor1, true, date, 90, "testDetails", room1);
		Lecture lecture3 = new Lecture(3, 10, course3, professor1, true, date, 90, "testDetails", room1);

		List<Lecture> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		when(lectureRepository.findAll()).thenReturn(lectures);
		when(bookingRepository.findByLecture(anyObject())).thenReturn(new ArrayList<>());

		   try{
			   professorServiceImpl.notifyProfessorsAboutNumberOfStudents();
		   }
		   catch(Exception e){
		      fail("Should not have thrown any exception");
		   }
		
	}

}