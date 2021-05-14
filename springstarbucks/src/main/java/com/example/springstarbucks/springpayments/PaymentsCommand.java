package com.example.springstarbucks.springpayments;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Payments")
@Data
@RequiredArgsConstructor
class PaymentsCommand {

    private @Id @GeneratedValue Long id;

    transient private String action ;
    private String firstname ;
    private String lastname ;

    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String cardnum;
    private String expmonth;
    private String expyear;
    private String cvv;
    private String email;
    private String notes;

    private String orderNumber;
    private String transactionAmount;
    private String transactionCurrency;
    private String authId;
    private String authStatus;
    private String captureId;
    private String captureStatus;

    
}
