package com.example.springstarbucks.Authentication.service;

import com.example.springstarbucks.Authentication.model.User;
import com.example.springstarbucks.Authentication.web.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
