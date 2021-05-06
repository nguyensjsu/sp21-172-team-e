package com.example.springstarbucks.drinks;

public class Latte implements Drink{


    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "Our dark, rich espresso balanced with steamed milk and a light layer of foam. A perfect milk-forward warm-up.";
    }

    @Override
    public String getCost() {
        // TODO Auto-generated method stub
        return "3.25";
    }
    
}
