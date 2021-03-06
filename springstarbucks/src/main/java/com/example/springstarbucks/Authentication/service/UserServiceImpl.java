package com.example.springstarbucks.Authentication.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springstarbucks.Authentication.model.Role;
import com.example.springstarbucks.Authentication.model.User;
import com.example.springstarbucks.Authentication.repository.UserRepository;
import com.example.springstarbucks.Authentication.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }




    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(
            registrationDto.getUsername(),
            registrationDto.getEmail(), 
            passwordEncoder.encode(registrationDto.getPassword()),
            Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.get(0).getEmail(), user.get(0).getPassword(), mapRolesToAuthorities(user.get(0).getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    
}
