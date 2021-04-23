// package com.example.springstarbucks.cardapi;


// import java.util.List;

// import com.example.springstarbucks.cardapi.Cards;
// import com.example.springstarbucks.cardapi.ICardService;
// import com.example.springstarbucks.orderapi.StarbucksCardRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;


// @Service
// public class CardServiceImpl implements ICardService{

//     @Autowired
//     StarbucksCardRepository cardRepository;

//     //check the health status of the API's
//     @Override
//     public String healthcheck() {
//         return "Starbucks API version 1.0 alive!";
//     }

//     //get all the starbucks cards from h2 database
//     @Override
//     public List<Cards> getAllStarbucksCards() {
//         return cardRepository.findAll();
//     }


//     //add a new startbucks card to h2 database
//     @Override
//     public String postStarbucksCard(Cards card) {
//         try{
//             cardRepository.save(card);
//             return "Card Saved to database successfully !";
//         }
//         catch(Exception e){
//             e.printStackTrace();
//             return "Error in saving the value";
//         }        
//     }

//     //get the specific cardnumber value from the h2 database
//     @Override
//     public Cards getSpecificStarbucksCard(String cardNumber) {
//         return cardRepository.findBycardNumber(cardNumber);    
//     }

//     //activate the starbucks card active-> true
//     @Override
//     public String activateStarbucksCardTrue(String cardNumber, int code) {
//         try{
//             Cards card = cardRepository.findBycardNumberAndCardCode(cardNumber,code);       //used springboot jpa query with two columns 
//             card.setActivated(true);  
//             cardRepository.save(card);
//             return "Activated the card successfully !";
//         }
//         catch(Exception e){
//             e.printStackTrace();
//             return "Error in activating the card !";
//         }   
//     }

//     //delete all starbucks cards
//     @Override
//     public String deleteStarbucksCards() {
//         try {
//             cardRepository.deleteAll();
//             return "All Cards Cleared!";
//         }
//         catch(Exception e){
//             e.printStackTrace();
//             return "Error in clear card !";
//         }
//     }

    
// }
