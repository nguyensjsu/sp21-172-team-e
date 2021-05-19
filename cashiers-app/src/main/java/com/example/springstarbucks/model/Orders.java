package com.example.springstarbucks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ORDERS")  //order is received keyword in H2DB. so used the orders instead of it.
public class Orders {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)	
    private int id;

    @Column(name = "drink")
    @NotEmpty(message = "drink cannot be empty")
    private String drink;

    @Column(name = "milk")
    @NotEmpty(message = "milk cannot be empty")
    private String milk;

    @Column(name = "size")
    @NotEmpty(message = "size cannot be empty")
    private String size;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private String status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDrink() {
        return drink;
    }
    public void setDrink(String drink) {
        this.drink = drink;
    }
    public String getMilk() {
        return milk;
    }
    public void setMilk(String milk) {
        this.milk = milk;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    
}
