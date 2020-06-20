package com.latam.birthday.test.services.service;

import com.latam.birthday.test.dto.UserCreateDto;
import com.latam.birthday.test.entities.UsersEntity;

public interface UserService {

    /**
     *
     * @param userDto UserCreateDto Data transfer with minimal data to create user
     * @return UserEntity user entity with full data
     * @author Jorge Silva Aguilera
     */
    UsersEntity create(UserCreateDto userDto);
}
