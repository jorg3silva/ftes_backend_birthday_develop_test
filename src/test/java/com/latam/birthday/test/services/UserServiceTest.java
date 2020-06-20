package com.latam.birthday.test.services;

import com.latam.birthday.test.BirthdayRestApiApplication;
import com.latam.birthday.test.dto.UserCreateDto;
import com.latam.birthday.test.entities.UsersEntity;
import com.latam.birthday.test.exceptions.BusinessException;
import com.latam.birthday.test.services.service.UserService;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = BirthdayRestApiApplication.class)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void createSplitNamesTest() {
        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("firstname middlename");
        u.setLastNames("lastname secondsurname");
        u.setBirthDate("03-04-1992");

        UsersEntity uResp = this.userService.create(u);

        assertEquals(uResp.getFirstName(), "firstname");
    }


    @Test
    public void createWrongDateTest() {
        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("firstname middlename");
        u.setLastNames("lastname secondsurname");
        u.setBirthDate("1992");


        assertThatThrownBy(() -> this.userService.create(u))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Cant parse birthdate.");
    }


    @Test
    public void createFutureDateTest() {

        LocalDate birthdate =  LocalDate.now();
        birthdate = birthdate.plusYears(10); // some future date

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("firstname middlename");
        u.setLastNames("lastname secondsurname");
        u.setBirthDate(birthdate.format(formatter));


        assertThatThrownBy(() -> this.userService.create(u))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Cant use a future date as birthdate");
    }


    @Test
    public void createTodayBirthdayTest() {

        LocalDate birthdate =  LocalDate.now();
        birthdate = birthdate.plusYears(-10); // 10 years ago
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("firstname middlename");
        u.setLastNames("lastname secondsurname");
        u.setBirthDate(birthdate.format(formatter));

        UsersEntity uResp = this.userService.create(u);

        assertEquals(uResp.getLeftDays(), 0);
        assertTrue(uResp.isTodayBirthday());
    }

    @Test
    public void createPoemTest() {

        LocalDate birthdate =  LocalDate.now();
        birthdate = birthdate.plusYears(-10); // 10 years ago
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("firstname middlename");
        u.setLastNames("lastname secondsurname");
        u.setBirthDate(birthdate.format(formatter));

        UsersEntity uResp = this.userService.create(u);

        assertNotNull(uResp.getPoem());
        assertNotNull(uResp.getPoet());
    }


    @Test
    public void createTodayBirthTest() {

        LocalDate birthdate =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("firstname middlename");
        u.setLastNames("lastname secondsurname");
        u.setBirthDate(birthdate.format(formatter));

        UsersEntity uResp = this.userService.create(u);

        assertTrue( uResp.getLeftDays() == 365 || uResp.getLeftDays() == 366 );
    }

}
