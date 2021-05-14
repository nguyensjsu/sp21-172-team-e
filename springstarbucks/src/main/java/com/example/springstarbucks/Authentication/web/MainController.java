package com.example.springstarbucks.Authentication.web;

import java.util.ArrayList;
import java.util.List;

import com.example.springstarbucks.Authentication.model.User;
import com.example.springstarbucks.Authentication.repository.UserRepository;
import com.example.springstarbucks.Authentication.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
    
    private final UserRepository UserRepository;
    public MainController(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/")
    public ModelAndView home(@AuthenticationPrincipal OidcUser user) {
        ModelAndView mav = new ModelAndView();
        if(user != null){
            String token = user.getIdToken().getTokenValue();
            List<User> userToken = UserRepository.findByToken(token);
            User event;
            if (userToken.isEmpty()) {
                event = new User(user.getSubject(), user.getClaims().get("name").toString(),token);
                UserRepository.save(event);
            }else{
                event = userToken.get(0);
            }
            UserRepository.save(event);

            List<User> eventsToShow;
            boolean isAdmin = user.getUserInfo().getClaimAsStringList("groups").contains("Admin");
            if (isAdmin) {
                eventsToShow = UserRepository.findAll();
            } else {
                eventsToShow = UserRepository.findByUserId(user.getSubject());
            }

            mav.addObject("user", user.getUserInfo());
            mav.addObject("idToken", user.getIdToken().getTokenValue());
            mav.addObject("userEvents",eventsToShow);
            mav.addObject("isAdmin",isAdmin);
        }
       
        mav.setViewName("newhome");
        return mav;
    }
  
  
    // public String home(){
    //     return "newhome";
    // }

    @Autowired
    UserRepository userR;



    @ModelAttribute("loggedInUser")
    public List<User> globalUserObject(@ModelAttribute("user") @Validated UserRegistrationDto userDto, BindingResult result,  Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        if(loggedInUser instanceof AnonymousAuthenticationToken || loggedInUser == null) return new ArrayList<>();


        String email = loggedInUser.getName(); 
        List<User> customerizedUser = userR.findByEmail(email);
        if(!customerizedUser.isEmpty()){
            String username = customerizedUser.get(0).getUsername();
            model.addAttribute("username", username);
        }else{
            List<User> oktaUser = userR.findByUserId(email);
            if(oktaUser.isEmpty()) return new ArrayList<>();
            customerizedUser = oktaUser;
            String username = oktaUser.get(0).getAuthName();
            model.addAttribute("username", username);
        }
        return customerizedUser;
    }
}
