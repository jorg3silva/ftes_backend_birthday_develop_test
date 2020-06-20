package com.latam.birthday.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootTest(classes = BirthdayRestApiApplication.class)
@ServletComponentScan
@AutoConfigureMockMvc
class BirthdayRestApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
