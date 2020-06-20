package com.latam.birthday.test.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.latam.birthday.test.dto.PoemDto;
import com.latam.birthday.test.dto.PoetDto;
import com.latam.birthday.test.dto.UserCreateDto;
import com.latam.birthday.test.entities.UsersEntity;
import com.latam.birthday.test.exceptions.BusinessException;
import com.latam.birthday.test.helpers.ConstantHelper;
import com.latam.birthday.test.helpers.PropertiesHelper;
import com.latam.birthday.test.helpers.RestHelper;
import com.latam.birthday.test.helpers.UtilHelper;
import com.latam.birthday.test.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PropertiesHelper property;

    @Override
    public UsersEntity create(UserCreateDto userDto) {

        String[] names = userDto.getFirstNames().split(" ");
        String[] lastNames = userDto.getLastNames().split(" ");

        // set names splitted: ex: John George => firstName = John, middleName = George.
        String firstname =  names.length >= 1 ? names[0] : "";
        String middlename = names.length >= 2 ? names[1] : "";

        // set lastnames
        String lastname = lastNames.length >= 1 ? lastNames[0] : "";
        String secondsurname = lastNames.length >= 2 ? lastNames[1] : "";

        // parse str date to Date.
        Date birthDate;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            birthDate = formatter.parse(userDto.getBirthDate());
        } catch (Exception e) {
            throw new BusinessException("Cant parse birthdate.");
        }

        if ( birthDate.after((new Date()))) {
            throw new BusinessException("Cant use a future date as birthdate");
        }

        // Instance of entity
        UsersEntity user = new UsersEntity( firstname, middlename, lastname, secondsurname, birthDate);

        // set age
        user.setAge(UtilHelper.getDateDiffInYearToNow(user.getBirthDate()));

        // set next birthday
        int nextBirthDate = UtilHelper.getDaysForNextDayOfMonth(user.getBirthDate());
        user.setLeftDays(nextBirthDate);

        // set if birthday is today
        user.setTodayBirthday(nextBirthDate == 0);


        // get poem
        if ( user.isTodayBirthday() ) {
            try {
                String poemJson = this.getPoem();

                ObjectMapper mapper = new ObjectMapper();
                PoemDto[] poems = mapper.readValue(poemJson, PoemDto[].class);

                Random rand = new Random();

                PoemDto poem = poems[rand.nextInt(poems.length)];
                user.setPoem(poem.getContent());
                user.setPoet(poem.getPoet().getName());

            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("Cant get poem, failed to connect to the API.");
            }
        }

        return user;

    }


    /**
     *
     * @return Poem
     */
    private String getPoem() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(ConstantHelper.HTTPS)
                .host(this.property.getPoemHost())
                .path(this.property.getPoemPath());
        HttpEntity<String> entity = RestHelper.createHeaderAndJson(ConstantHelper.EMPTY, new Gson().toJson(new ArrayList<>()));

        try {
            return this.callToService(builder, entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     *
     *      Call to service
     *
     * @param builder b
     * @param entity e
     * @throws Exception ex
     */
    private String callToService(UriComponentsBuilder builder, HttpEntity<String> entity) throws Exception {
        int access = 0;
        String errorMessage = ConstantHelper.EMPTY;

        while (this.property.getRetries() > access) {
            try {
                return RestHelper.callService(builder.toUriString(), HttpMethod.GET , entity);
            } catch (ResourceAccessException uce) {
                access++;
                errorMessage = uce.getMessage();
            }
        }

        throw new Exception(errorMessage);
    }
}
