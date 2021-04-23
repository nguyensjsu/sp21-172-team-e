// package com.example.springstarbucks.cardapi;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import java.util.List;

// import com.example.springstarbucks.cardapi.CardServiceImpl;
// import com.example.springstarbucks.cardapi.Cards;

// @Controller
// public class CardController {

// 	@Autowired
// 	CardServiceImpl cardServiceImpl;

// 	//check the api's working correctly api
// 	@RequestMapping(value="/ping", method=RequestMethod.GET)
// 	@ResponseBody
// 	public String healthCheck() {
// 		return cardServiceImpl.healthcheck();
// 	}

	

	
// 	// ********************************* starbucks cards***************************************************//


// 	//get all the starbuck cards api
// 	@RequestMapping(value="/getcards", method=RequestMethod.GET)
// 	@ResponseBody
// 	public List<Cards> getAllStarBucksCards() {
// 		return cardServiceImpl.getAllStarbucksCards();
// 	}

// 	//Submit the starbuck card api
// 	@RequestMapping(value="/newcard", method=RequestMethod.POST)
// 	@ResponseBody
// 	public String createStarbucksCard(Cards cards) {
// 		return cardServiceImpl.postStarbucksCard(cards);
// 	}

// 	//find the starbucks card api 
// 	@RequestMapping(value="/findcard", method = RequestMethod.GET)
// 	@ResponseBody
// 	public Cards findStarbucksCard(@RequestParam("cardnumber") String cardNumber) {
// 		System.out.println(cardNumber);
// 		return cardServiceImpl.getSpecificStarbucksCard(cardNumber);
// 	}

// 	//Activate starbuck card api
// 	@RequestMapping(value="/card/activate", method=RequestMethod.POST)
// 	@ResponseBody
// 	public String activateStarbucksCard(@RequestParam("cardnumber") String cardNumber, @RequestParam("code") int code) {
// 		return cardServiceImpl.activateStarbucksCardTrue(cardNumber, code);
// 	}

// 	//delete all the starbucks records
// 	@RequestMapping(value="/cards", method = RequestMethod.DELETE)
// 	@ResponseBody
// 	public void deleteAllCards(){
// 		cardServiceImpl.deleteStarbucksCards();
// 	}


// }

