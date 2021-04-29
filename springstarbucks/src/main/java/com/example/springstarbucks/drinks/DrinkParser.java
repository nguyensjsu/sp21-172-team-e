package com.example.springstarbucks.drinks;

public class DrinkParser {

    private String drinkname;
    public DrinkParser(String drinkname) {

        this.drinkname = drinkname;
    }

    public Drink setDrink() {

        if(this.drinkname.equals("Frappe")) {
            return new Frappe();
        } 


        return null;
    }
    
}
