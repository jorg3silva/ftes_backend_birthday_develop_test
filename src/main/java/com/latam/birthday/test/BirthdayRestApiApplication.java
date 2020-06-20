package com.latam.birthday.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.TimeZone;

@ServletComponentScan
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BirthdayRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthdayRestApiApplication.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-4"));
	}

}
