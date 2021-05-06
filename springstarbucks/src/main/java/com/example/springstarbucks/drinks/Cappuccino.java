package com.example.springstarbucks.drinks;

public class Cappuccino implements Drink{

    @Override
    public String getMessage() {
        
        return "Dark, rich espresso lies in wait under a smoothed and stretched layer of thick milk foam. An alchemy of barista artistry and craft.";
    }

    @Override
    public String getCost() {
        // TODO Auto-generated method stub
        return "3.50";
    }
    
}
