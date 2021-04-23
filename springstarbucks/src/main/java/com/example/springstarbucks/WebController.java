package com.example.springstarbucks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/menupage/drink/")
	public String drinktemplate(@RequestParam(value="drink", required=true) String drink, Model model) {
		model.addAttribute("drink", drink);
		return "drinktemplate";
	}
}

