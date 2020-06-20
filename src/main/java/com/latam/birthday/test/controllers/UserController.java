package com.latam.birthday.test.controllers;


import com.latam.birthday.test.dto.UserCreateDto;
import com.latam.birthday.test.entities.UsersEntity;
import com.latam.birthday.test.helpers.StandarResponseHelper;
import com.latam.birthday.test.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    /**
     *
     *      Receive an user json object (fullname and birthdate), valdiate and process data,
     *      then return a JSON with firstname, lastname, age, left days to birthday and a poem
     *      if the user birthday is today.
     *
     *      Ej. of response.
     *
     *      {
     *          status: success,
     *          message: 'user retrieve',
     *          data: {
     *              firstname: '',
     *              lastname: '',
     *              birthdate: 'DD/MM/YY'
     *              age: 0,
     *              todayBirthDay: true,
     *              poem: 'lorem ipsum'
     *          }
     *      }
     *
     *      Ej. in Fail:
     *
     *      {
     *          status: fail,
     *          message: 'error message'
     *      }
     *
     * @param userCreateDto UserCreateDto
     * @return ResponseEntity<User>
     */
    @PostMapping(path="/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateDto userCreateDto ){

        UsersEntity user = this.userService.create(userCreateDto);

        return ResponseEntity.ok(StandarResponseHelper.success(user.getPublicData(), "User retrieve with success"));

    }
}
