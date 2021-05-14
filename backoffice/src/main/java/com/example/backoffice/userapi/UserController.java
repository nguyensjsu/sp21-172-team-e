package com.example.backoffice.userapi;

import java.util.List;
import java.util.Random;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication; //for getting logged in user info
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
class UserController{
    private final UserRepository repository; 

    UserController(UserRepository repository){
        this.repository = repository; 
    }

    @PostMapping("/add")
    Message addPoints(@PathVariable String email, @PathVariable String points){
        User user = repository.findByEmail(email);
        int rewards = Integer.parseInt(points); //get points to add 
        user.setPoints(user.getPoints()+rewards);
        repository.save(user);
        Message msg = new Message(); 
        msg.setStatus("New points : " + user.getPoints());
        return msg;

        //TODO: Add ResponseStatusException 
    }
}