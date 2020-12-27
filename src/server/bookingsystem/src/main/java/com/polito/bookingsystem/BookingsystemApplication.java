package com.polito.bookingsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.polito.bookingsystem.utils.FileStorageProperties;

@SpringBootApplication 
@EnableScheduling
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class BookingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsystemApplication.class, args);
	}

}
