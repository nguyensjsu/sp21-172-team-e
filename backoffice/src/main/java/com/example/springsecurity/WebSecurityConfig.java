package com.example.backoffice;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  //specifies which web pages are secured and which are not 
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
      .logout()
        .permitAll();
  }

  //creates a SINGLE USER FOR LOGIN, replace later 
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user =
      User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
  }

/*
  @Autowired
  private UserService userService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
      auth.setUserDetailsService(userService);
      auth.setPasswordEncoder(passwordEncoder());
      return auth;
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.authenticationProvider(authenticationProvider());
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception{
      http.authorizeRequests().antMatchers(
          "/",
          "/registration**",
          "/js/**",
          "/css/**",
          "/images/**",
          "/icons/**",
          "/login/oauth2/authorization/**",
          "/login/oauth2/*",
          "/login/oauth2/callback/okta").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .permitAll()
      .and()
      .logout()
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/?logout")
      .permitAll()
      .and()
      .oauth2Client()
      .and()
      .oauth2Login()
      .loginPage("/login")
      .authorizationEndpoint()
      .baseUri("/login/oauth2/authorization");
  }

  
  */
}