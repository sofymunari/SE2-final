package com.polito.bookingsystem.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.polito.bookingsystem.dto.ProfessorDto;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.ProfessorService;
import com.polito.bookingsystem.service.StudentService;

@Controller
public class FileController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	ProfessorService professorService;

    @GetMapping("/managerportal/file/tracereport/student/{email}/{dateString}")
    public String welcome(Map<String, Object> model, @PathVariable String email, @PathVariable String dateString) throws ParseException {
    	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = sdformat.parse(dateString);
    	StudentDto studentDto = studentService.getStudent(email);
        model.put("sourceType", "Student");
        model.put("source", studentDto);
        model.put("contactedStudents", studentService.getContactedStudents(studentDto, date));
        model.put("contactedProfessors", studentService.getContactedProfessors(studentDto, date));

        return "welcome";
    }
    
    @GetMapping("/managerportal/file/tracereport/professor/{email}/{dateString}")
    public String welcome2(Map<String, Object> model, @PathVariable String email, @PathVariable String dateString) throws ParseException {
    	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = sdformat.parse(dateString);
    	ProfessorDto professorDto = professorService.getProfessor(email);
        model.put("sourceType", "Professor");
        model.put("source", professorDto);
        model.put("contactedStudents", professorService.getContactedStudents(professorDto, date));
        return "welcome";
    }

}