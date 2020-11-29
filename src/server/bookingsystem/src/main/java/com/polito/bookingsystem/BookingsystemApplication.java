package com.polito.bookingsystem;
import java.text.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookingsystemApplication {

	
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(BookingsystemApplication.class, args);
	}

}
