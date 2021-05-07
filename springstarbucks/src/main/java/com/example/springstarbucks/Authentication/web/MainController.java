package com.example.springstarbucks.Authentication.web;

import com.example.springstarbucks.Authentication.model.User;
import com.example.springstarbucks.Authentication.repository.UserRepository;
import com.example.springstarbucks.Authentication.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class MainController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String home(){
        return "homePage";
    }

    @Autowired
    UserRepository userR;



    @ModelAttribute("loggedInUser")
    public User globalUserObject(@ModelAttribute("user") @Validated UserRegistrationDto userDto, BindingResult result,  Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        if(loggedInUser instanceof AnonymousAuthenticationToken) return null;

        String email = loggedInUser.getName(); 
        User user = userR.findByEmail(email);
        String firstname = user.getFirstName();
        model.addAttribute("firstName", firstname);
        model.addAttribute("emailAddress", email);
        return user;

    }
}
