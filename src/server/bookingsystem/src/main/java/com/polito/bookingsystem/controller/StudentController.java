package com.polito.bookingsystem.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.StudentService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge= 3000)
@RestController
public abstract class StudentController {
	@Autowired
	StudentService studentService;
	
	@GetMapping(value="studentinfo/{email}")
	public StudentDto getStudentInfo(@PathVariable String email) {
        return studentService.getStudent(email);
	}
	@PostMapping(value= "/studentlogin")
	public String login(@RequestBody Map<String, String> userPass) {
			return studentService.login(userPass.get("username"), userPass.get("password"));
	}
}
