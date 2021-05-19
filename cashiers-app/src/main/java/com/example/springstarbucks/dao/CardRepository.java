package com.example.springstarbucks.dao;

import com.example.springstarbucks.model.*;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<Cards, Integer>{

    Cards findBycardNumber(String cardNumber);
    Cards findBycardNumberAndCardCode(String cardNumber, int cardCode);

}