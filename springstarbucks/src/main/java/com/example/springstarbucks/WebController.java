package com.example.springstarbucks;

import javax.servlet.http.HttpServletRequest;


import com.example.springstarbucks.drinks.Drink;
import com.example.springstarbucks.drinks.DrinkParser;
import com.example.springstarbucks.orderapi.PaymentsCommand;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	@GetMapping("/")
	public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "home";
	}

	@GetMapping("/menupage")
	public String menu(String name, Model model) {
		return "menu";
	}

	@GetMapping("/rewardspage")
	public String rewards(String name, Model model) {
		return "rewards";
	}

	@GetMapping("/cardspage")
	public String cards(String name, Model model) {
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

