package com.latam.birthday.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latam.birthday.test.BirthdayRestApiApplication;
import com.latam.birthday.test.dto.UserCreateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = BirthdayRestApiApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTest() throws Exception {

        UserCreateDto u = new UserCreateDto();
        u.setFirstNames("Jorge Alejandro");
        u.setLastNames("Silva Aguilera");
        u.setBirthDate("04-03-1992");


        mockmvc.perform(
                MockMvcRequestBuilders
                .post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper().writeValueAsString(u)))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success")).andReturn();

    }

}
