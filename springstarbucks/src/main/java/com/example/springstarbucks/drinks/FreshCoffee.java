package com.example.springstarbucks.drinks;

public class FreshCoffee implements Drink{


    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "With an airtight seal, new Starbucks® Fresh Brew is the easiest way to open a fresher day, every day.";
    }

    @Override
    public String getCost() {
        // TODO Auto-generated method stub
        return "3.00";
    }
    
}
