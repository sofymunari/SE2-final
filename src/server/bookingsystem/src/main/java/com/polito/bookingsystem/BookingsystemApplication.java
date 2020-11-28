package com.polito.bookingsystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.polito.bookingsystem.service.ProfessorService;

@SpringBootApplication
@EnableScheduling
public class BookingsystemApplication {

	
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(BookingsystemApplication.class, args);
	}

}
