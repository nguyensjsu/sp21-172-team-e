package com.example.springstarbucks;
import com.example.springstarbucks.Cards;

import java.util.List;

public interface ICardService {

    String healthcheck();
    List<Cards> getAllStarbucksCards();
    String postStarbucksCard(Cards card);
    Cards getSpecificStarbucksCard(String cardNumber);
    String activateStarbucksCardTrue(String cardNumber, int code);
    String deleteStarbucksCards();
    

}
