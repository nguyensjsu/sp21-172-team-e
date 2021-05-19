package com.example.springstarbucks.services;
import com.example.springstarbucks.model.*;

import java.util.List;

public interface ICardService {

    String healthcheck();
    List<Cards> getAllStarbucksCards();
    String postStarbucksCard(Cards card);
    Cards getSpecificStarbucksCard(String cardNumber);
    String activateStarbucksCardTrue(String cardNumber, int code);
    void deleteStarbucksCards();
    

}
