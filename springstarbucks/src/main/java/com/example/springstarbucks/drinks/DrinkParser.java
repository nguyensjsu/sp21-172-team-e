package com.example.springstarbucks.drinks;

public class DrinkParser {

    private String drinkname;
    public DrinkParser(String drinkname) {

        this.drinkname = drinkname;
    }

    public Drink setDrink() {

        if(this.drinkname.equals("Frappe")) {
            return new Frappe();
        } else if (this.drinkname.equals("Cappuccino")) {
            return new Cappuccino();
        } else if (this.drinkname.equals("Latte")) {
            return new Latte();
        } else if(this.drinkname.equals("Fresh Coffee")) {
            return new FreshCoffee();
        }

        return null;
    }
    
}
