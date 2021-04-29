package com.example.springstarbucks.drinks;

public class Cappuccino implements Drink{

    @Override
    public String getMessage() {
        
        return "This is Cappuccino";
    }

    @Override
    public String getCost() {
        // TODO Auto-generated method stub
        return "3.50";
    }
    
}
