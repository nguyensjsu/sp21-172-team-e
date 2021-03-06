package com.example.springstarbucks.cardapi;

import java.util.List;
import java.util.Random;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication; //for getting logged in user info
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
class StarbucksCardController {

	private final StarbucksCardRepository repository;

	StarbucksCardController(StarbucksCardRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/cards")
	StarbucksCard newCard() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		StarbucksCard newcard = new StarbucksCard();
		Random random = new Random();
		int num = random.nextInt(900000000) + 100000000;
		int code = random.nextInt(900) + 100;

		newcard.setCardNumber(String.valueOf(num));
		newcard.setCardCode(String.valueOf(code));
		//TODO: Let user select the balance to load onto the card
		newcard.setBalance(20.00);
		newcard.setStatus("New Card");
		//TODO: Add 'setCardEmail' once we know how to set logged in user email
		//String user = loggedInUser.getName(); //this will get the email, NOT the first name
		return repository.save(newcard);
	}

	@GetMapping("/cards")
	List<StarbucksCard> all() {
		return repository.findAll();
	}

	@DeleteMapping("/cards")
	HashMap<String,String> deleteAll() {

		repository.deleteAllInBatch();

		HashMap<String, String> map = new HashMap<>();
		String status = "All Cards Cleared!";
    	map.put("status", status);
		
		return map;
	}

	//baeldung.com/exception-handling-for-rest-with-spring
	//baeldung.com/spring-pathvariable
	@GetMapping("/card/{num}")
	StarbucksCard getOne(@PathVariable String num, HttpServletResponse response) {
		StarbucksCard card = repository.findByCardNumber(num);
		if(card == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
		return card;
	}

	@PostMapping("/card/activate/{num}/{code}")
	StarbucksCard activate(@PathVariable String num, @PathVariable String code, HttpServletResponse response) {
		StarbucksCard card = repository.findByCardNumber(num);

		if(card == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
		if(card.getCardCode().equals(code)) {
			card.setActivated(true);
			repository.save(card);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Valid!");
		}

		return card;
	}

	//Add dollars to Starbucks card
	
	@PostMapping("/card/{num}/{amount}")
	StarbucksCard addPoints(@PathVariable String num, @PathVariable String amount, HttpServletResponse response) {
		//first find card
		StarbucksCard card = repository.findByCardNumber(num);
		if(card == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");

		//Note: card does not have to be activated to load more onto it
		card.setBalance(Double.parseDouble(amount) + card.getBalance()); //add amount to balance, parses amount to add 
		repository.save(card);
		
		return card;
	}










}