package com.polito.bookingsystem.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.polito.bookingsystem.dto.StudentDto;
import com.polito.bookingsystem.service.StudentService;

@Controller
public class FileController {

	@Autowired
	StudentService studentService;

    @RequestMapping("/managerportal/file/tracereport/student/{email}/{dateString}")
    public String welcome(Map<String, Object> model, @PathVariable String email, @PathVariable String dateString) throws ParseException {
    	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = sdformat.parse(dateString);
    	StudentDto studentDto = studentService.getStudent(email);
        model.put("sourceStudent", studentDto);
        model.put("contactedStudents", studentService.getContactedStudents(studentDto, date));
        return "welcome";
    }

}