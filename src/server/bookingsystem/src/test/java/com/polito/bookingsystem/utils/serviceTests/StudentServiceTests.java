package com.polito.bookingsystem.utils.serviceTests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
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
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.RoomDto;
import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.entity.Course;
import com.polito.bookingsystem.entity.Professor;
import com.polito.bookingsystem.entity.Student;
import com.polito.bookingsystem.repository.CourseRepository;
import com.polito.bookingsystem.repository.StudentRepository;
import com.polito.bookingsystem.service.impl.StudentServiceImpl;
import com.polito.bookingsystem.utils.BookingInfo;
import com.polito.bookingsystem.service.BookingService;

@RunWith(SpringRunner.class)
@DataJpaTest
class StudentServiceTests {
	
	private BookingService bookingService;
	
	@Autowired
	private StudentRepository studentRepository;	
	
	@Autowired
	private CourseRepository courseRepository;	

	private JavaMailSender javaMailSender;
	
	private StudentServiceImpl studentServiceImpl;
	
	
	
	
	
	@BeforeEach
	public void setUp() throws Exception {
		bookingService = mock(BookingService.class);
		studentRepository = mock(StudentRepository.class);
		courseRepository = mock(CourseRepository.class);
		javaMailSender = mock( JavaMailSender.class);
		studentServiceImpl = new StudentServiceImpl(studentRepository, courseRepository, javaMailSender,bookingService);
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
		Course course1 = new Course(1, "testName", "A", 1,1);
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
		Course course1 = new Course(1, "testName", "A", 1,1);
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
		Course course1 = new Course(1, "testName", "A", 1,1);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		
		when(studentRepository.findByEmail(anyString())).thenReturn(student);

		assertEquals("Expected test@email.com to be returned, successful login", "test@email.com", studentServiceImpl.login("test@email.com", "testPassword"));
	}
	
	@Test
	void testLoginStudent4() {
		//passing a null mail and a null password
		
		when(studentRepository.findByEmail(anyString())).thenReturn(null);
		
		assertNull("Expected a null value returned, invalid email and password", studentServiceImpl.login(null, null));
	}
	
	@Test
	void testSendEmail1(){
	   try{
	      studentServiceImpl.sendEmail(null, "test", "test");
	   }
	   catch(Exception e){
	      fail("Should not have thrown any exception");
	   }
	}
	
	@Test
	void testSendEmail2(){
	   try{
			SimpleMailMessage msg = new SimpleMailMessage();

		   Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		   CourseDto course1 = new CourseDto(1, "testName", "A", 1,1);
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
	
	@Test
	void testGetContacetedStudents() throws ParseException {
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");//da passare alla funzione
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("03/01/0101");
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/0101");
		Date date0 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/0100");
		CourseDto course1 = new CourseDto(1, "testName", "testDescription",1,1);
		List<CourseDto> courses = new ArrayList<>();
		List<BookingDto> bookings = new ArrayList<>();
		List<BookingDto> bookings1 = new ArrayList<>();
		List<BookingDto> bookings2 = new ArrayList<>();
		List<LectureDto> lectures = new ArrayList<>();
		LectureDto lecture0 = new LectureDto(1,2,course1,null,false,date0,120,"testDescription",null);
		LectureDto lecture = new LectureDto(1,2,course1,null,false,date1,120,"testDescription",null);
		LectureDto lecture1 = new LectureDto(2,2,course1,null,false,date2,120,"testDescription",null);
		StudentDto testStud = new StudentDto(2,"testName","testSurname","testAddress","test@email.com","testPassword",date,courses,"testMatricola");
		StudentDto testStud1 = new StudentDto(3,"testName","testSurname","testAddress","test@email.com","testPassword",date,courses,"testMatricola");
		StudentDto testStud2 = new StudentDto(3,"testName","testSurname","testAddress","test2@email.com","testPassword",date,courses,"testMatricola");
		BookingDto booking0 = new BookingDto(1,testStud,lecture0,BookingInfo.BOOKED);
		BookingDto booking = new BookingDto(1,testStud,lecture,BookingInfo.BOOKED);
		courses.add(course1);
		StudentDto covidStudent = new StudentDto(1,"testName","testSurname","testAddress","test1@email.com","testPassword",date,courses,"testMatricola");
		BookingDto booking1 = new BookingDto(2,covidStudent,lecture1,BookingInfo.BOOKED);
		BookingDto booking2 = new BookingDto(3,testStud1,lecture1,BookingInfo.BOOKED);
		BookingDto booking3 = new BookingDto(3,testStud2,lecture1,BookingInfo.BOOKED);
		bookings.add(booking);
		bookings.add(booking1);
		bookings.add(booking0);
		bookings1.add(booking);
		bookings2.add(booking1);
		bookings2.add(booking2);
		bookings2.add(booking3);
		when(bookingService.getListBooking(anyObject())).thenReturn(bookings);
		when(bookingService.getBookingsByLecture(anyObject())).thenReturn(bookings2).thenReturn(bookings2).thenReturn(bookings1);
		assertTrue(studentServiceImpl.getContactedStudents(covidStudent, date).size()==2);
		
	}
	
	@Test
	void testGetContacetedStudents1() throws ParseException {
		//invalid params
		assertTrue(studentServiceImpl.getContactedStudents(null, null).isEmpty());
	}
	
	
	@Test
	void testAddStudents1() throws ParseException {
		String fileName = "../../test-files/Students.csv";
		
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		List<Student> students = new ArrayList<>();
		
		Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		students.add(student);
		
		try {
		     
			when(studentRepository.findByMatricola(anyObject())).thenReturn(student).thenReturn(null);
			when(studentRepository.findAll()).thenReturn(students);
		
			when(studentRepository.save(anyObject())).thenReturn(null);
			studentServiceImpl.addStudents(fileName);
			
		} catch (Exception e) {
		          fail("should't come here. exception wrongly thrown");
		}
	}
	
	@Test
	void testAddStudents2() {
		String fileName = "test-wrong";
		IOException e= new IOException();
		try {
			studentServiceImpl.addStudents(fileName);
			
		}catch (Exception ex) {
			fail("Shouldn't come here");
		}
	}
	
	@Test
	void testAddClasses1() throws ParseException  {
		String fileName = "../../test-files/Enrollment.csv";
		
		
		Course course1 = new Course(1, "testName1", "A",1,1);
		Course course2 = new Course(2, "testName2", "B",1,1);
		Course course3 = new Course(3, "testName3", "C",1,1);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		List<Student> students = new ArrayList<>();
		
		Student student = new Student(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", date, courses, "testMatricola");
		students.add(student);
		
		try {
		     
			when(studentRepository.findByMatricola(anyObject())).thenReturn(student).thenReturn(null);
			
			when(courseRepository.findByCode(anyObject())).thenReturn(course1).thenReturn(null);
			when(studentRepository.save(anyObject())).thenReturn(null);
			studentServiceImpl.addClasses(fileName);
			
		} catch (Exception e) {
		          fail("should't come here. exception wrongly thrown");
		}
	}
	
	@Test
	void testAddClasses2() {
		String fileName = "test-wrong";
		IOException e= new IOException();
		try {
			studentServiceImpl.addClasses(fileName);
			
		}catch (Exception ex) {
			fail("Shouldn't come here");
		}
	}
	
	@Test
	void testGetContactedProfessors() throws ParseException{
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");//input date
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/0100");
		CourseDto course1 = new CourseDto(1, "testName", "A", 1,1);
		CourseDto course2 = new CourseDto(2, "testName", "B", 1,1);
		List<CourseDto> courses = new ArrayList<>();
		//student courses
		courses.add(course1);
		courses.add(course2);
		//2 professors one for each course
		List<CourseDto> coursesProf1 = new ArrayList<>();
		List<CourseDto> coursesProf2 = new ArrayList<>();
		coursesProf1.add(course1);
		coursesProf2.add(course2);
		ProfessorDto professorDto = new ProfessorDto(1, "testName", "testSurname", "testAddress", "test@email.com", "testPassword", coursesProf1, "d0"); //input professor
		ProfessorDto professorDto1 = new ProfessorDto(2, "testName", "testSurname", "testAddress", "test@email1.com", "testPassword", coursesProf2, "d1");
		StudentDto student1 = new StudentDto(1, "testName", "testSurname", "testAddress", "test@email1.com", "testPassword", date, courses, "testMatricola1"); //input student
		BookingInfo bookingInfo = BookingInfo.BOOKED;
		RoomDto room = new RoomDto(1, "testName", 100);
		LectureDto lecture1 = new LectureDto(1, 10, course1, professorDto, true, date, 90, "testDetails", room);
		LectureDto lecture2 = new LectureDto(2, 10, course2, professorDto1, true, date, 90, "testDetails", room);
		LectureDto lecture3 = new LectureDto(2, 10, course2, professorDto1, true, date1, 90, "testDetails", room);//wrong day
		BookingDto booking1 = new BookingDto(1, student1, lecture1, bookingInfo);
		BookingDto booking2 = new BookingDto(2, student1, lecture2, bookingInfo);
		BookingDto booking4 = new BookingDto(2, student1, lecture2, bookingInfo);//same professor
		BookingDto booking3 = new BookingDto(2, student1, lecture3, bookingInfo);//wrong day
		List<BookingDto> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		bookings.add(booking4);
		when(bookingService.getListBooking(anyObject())).thenReturn(bookings);
		assertEquals("expected two professors",studentServiceImpl.getContactedProfessors(student1, date).size(),2);
		
	}
	
	@Test
	void testGetContactedProfessors2 () throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0101");
		List<ProfessorDto> contactedProfessors = new ArrayList<>();
		assertEquals("Expected empty list",studentServiceImpl.getContactedProfessors(null, date),contactedProfessors);
	}
	
	

}