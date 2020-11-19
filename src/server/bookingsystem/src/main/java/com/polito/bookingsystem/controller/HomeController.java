package com.polito.bookingsystem.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.polito.bookingsystem.dto.BookingDto;
import com.polito.bookingsystem.dto.LectureDto;
import com.polito.bookingsystem.dto.NotificationProfessorDto;
import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.BookingService;
import com.polito.bookingsystem.service.LectureService;
import com.polito.bookingsystem.service.NotificationProfessorService;
import com.polito.bookingsystem.service.NotificationStudentService;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.StudentService;
import com.polito.bookingsystem.utils.BookingEntry;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public class HomeController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	NotificationStudentService notificationStudentService;
	
	
	@Autowired
	NotificationProfessorService notificationProfessorService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test() {
		
		return "test";
	}
	
	
	
	@RequestMapping(value="studentinfo/{email}", method = RequestMethod.GET)
	public StudentDto getStudentInfo(@PathVariable String email) {
		
        return studentService.getStudent(email);
	}
	
	@RequestMapping(value="studentbookings/{email}", method = RequestMethod.GET)
	public List<BookingDto> getStudentBookings(@PathVariable String email) {
        return bookingService.getListBooking(email);
	}
	
	@RequestMapping(value="studentlectures/{email}", method = RequestMethod.GET)
	public List<LectureDto> getStudentLectures(@PathVariable String email) {
		List<BookingDto> bookingList=bookingService.getListBooking(email);
		List<Integer> ids=bookingList.stream().map(b->b.getLectureDto().getLectureId()).collect(Collectors.toList());
		List<LectureDto> availableLectures=lectureService.getListLectures(email).stream().filter(l->{
			for(Integer id: ids) {
				if(l.getLectureId()==id) {
					return false;
				}
			}
			return true;
		}).collect(Collectors.toList());
        return availableLectures;
	}
	
	
	@RequestMapping(value = "addbooking", headers="Content-Type=application/json", method = RequestMethod.POST)
    @ResponseBody
	public BookingDto addBooking(@RequestBody Map<String, String> lectureId) {
		 Integer lectId=Integer.parseInt(lectureId.get("lectureId"));
         return bookingService.addBooking(lectId, lectureId.get("email"));
	}
	
	
	@RequestMapping(value="deletebooking/{bookingId}", method = RequestMethod.DELETE)
	public Boolean deleteStudentBookings(@PathVariable String bookingId) {
		Integer bookingid=Integer.parseInt(bookingId);
        return bookingService.deleteBooking(bookingid);
	}
	
	
	@RequestMapping(value= "studentlogin", method= RequestMethod.POST)
	public String login(@RequestBody Map<String, String> userPass) {
			return studentService.login(userPass.get("username"), userPass.get("password"));
	}
	
	@RequestMapping(value= "professorlogin", method= RequestMethod.POST)
	public String loginProf(@RequestBody Map<String, String> userPass) {
			return professorService.login(userPass.get("username"), userPass.get("password"));
	}

	
	@RequestMapping(value= "professorbookings/{email}", method= RequestMethod.GET)
	public List<BookingEntry> getBooking(@PathVariable String email) {
			return bookingService.getBooking(email);
	}
	
	@RequestMapping(value="professorinfo/{email}", method = RequestMethod.GET)
	public ProfessorDto getProfessorInfo(@PathVariable String email) {
        return professorService.getProfessor(email);
	}
	
	
}
