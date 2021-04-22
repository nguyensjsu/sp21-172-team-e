package com.example.springstarbucks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARD")
public class Cards {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardCode")
    private int cardCode;

    @Column(name = "balance")
    private double balance;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "status")
    private String status;


    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public int getCardCode() {
        return cardCode;
    }
    public void setCardCode(int cardCode) {
        this.cardCode = cardCode;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean isActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



}
