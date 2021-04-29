package com.example.springstarbucks.drinks;

public class Frappe implements Drink {

    private String message;
    @Override
    public String message() {
        
        this.message = "This is a Frappe";

        return message;
    }
    
}
