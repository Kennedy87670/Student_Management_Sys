package com.forAll.sms.service;

import com.forAll.sms.dto.UserDto;
import com.forAll.sms.entity.User;

import java.util.List;

public interface UserService {


    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
