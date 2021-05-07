package com.example.springstarbucks;

import javax.servlet.http.HttpServletRequest;


import com.example.springstarbucks.drinks.Drink;
import com.example.springstarbucks.drinks.DrinkParser;
import com.example.springstarbucks.orderapi.PaymentsCommand;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

	/*@GetMapping("/")
	public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "home";
	}*/

	@GetMapping("/menupage")
	public String menu(String name, Model model) {
		return "menu";
	}

	@GetMapping("/rewardspage")
	public String rewards(String name, Model model) {
		return "rewards";
	}

	@PostMapping("/cardspage")
	public String cardsPost(String name,Model model,HttpServletRequest request) {

		String uri = "http://localhost:8080/cards";

		
	    RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		//map.add("email", "first.last@example.com");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity( uri, req , String.class );

		

	    String cardsList = restTemplate.getForObject(uri, String.class);
		model.addAttribute("cardsList", cardsList);
		return "cards";
	}

	@GetMapping("/cardspage")
	public String cardsGet(String name, Model model) {

		String uri = "http://localhost:8080/cards";
	    RestTemplate restTemplate = new RestTemplate();
	    String cardsList = restTemplate.getForObject(uri, String.class);
		model.addAttribute("cardsList", cardsList);
		return "cards";
	}

	//Goal: pass the drink value into the drink template
	@GetMapping("/menupage/{drinkname}")
	public String drinktemplate(@PathVariable("drinkname") String drinkname, Model model) {

		
		model.addAttribute("drinkname", drinkname);

		Drink drink = new DrinkParser(drinkname).setDrink();
		String message = drink.getMessage() + " costs " + drink.getCost();
		model.addAttribute("cost", drink.getCost());
		model.addAttribute("message", message);
		String image = "/images/" + drinkname.toLowerCase() + ".png";
		model.addAttribute("drink_image",image);



		return "drinktemplate";
	}

}

