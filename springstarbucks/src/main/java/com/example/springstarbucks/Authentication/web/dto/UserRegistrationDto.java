package com.example.springstarbucks.Authentication.web.dto;


public class UserRegistrationDto {
    private String username;
    private String email;
    private String password;
    private int rewardPoints; 

    public UserRegistrationDto(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.rewardPoints = 0; //initialize with 0 reward points 
    }

    public UserRegistrationDto() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRewards(int rewards){
        this.rewardPoints = rewards;
    }

    public int getRewards(){
        return this.rewardPoints;
    }
}
