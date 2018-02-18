package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import java.security.Principal;

public interface UserService {

    UserDto.Login getCurrentUser();
    User getUesr(Principal user);

}
