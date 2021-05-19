package com.example.springstarbucks.controllers;

import com.example.springstarbucks.servicesImpl.CardServiceImpl;
import com.example.springstarbucks.servicesImpl.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springstarbucks.model.*;
import java.util.List;

@Controller
public class CardController {

	@Autowired
	CardServiceImpl cardServiceImpl;

	@Autowired
	OrderServiceImpl orderServiceImpl;


	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "home";
	}

	//check the api's working correctly api
	@RequestMapping(value="/ping", method=RequestMethod.GET)
	@ResponseBody
	public String healthCheck() {
		return cardServiceImpl.healthcheck();
	}

	
	// ********************************* starbucks cards***************************************************//


	//get all the starbuck cards api
	@RequestMapping(value="/getcards", method=RequestMethod.GET)
	@ResponseBody
	public List<Cards> getAllStarBucksCards() {
		return cardServiceImpl.getAllStarbucksCards();
	}

	//Submit the starbuck card api
	@RequestMapping(value="/newcard", method=RequestMethod.POST)
	@ResponseBody
	public String createStarbucksCard(Cards cards) {
		return cardServiceImpl.postStarbucksCard(cards);
	}

	//find the starbucks card api 
	@RequestMapping(value="/findcard", method = RequestMethod.GET)
	@ResponseBody
	public Cards findStarbucksCard(@RequestParam("cardnumber") String cardNumber) {
		System.out.println(cardNumber);
		return cardServiceImpl.getSpecificStarbucksCard(cardNumber);
	}

	//Activate starbuck card api
	@RequestMapping(value="/card/activate", method=RequestMethod.POST)
	@ResponseBody
	public String activateStarbucksCard(@RequestParam("cardnumber") String cardNumber, @RequestParam("code") int code) {
		return cardServiceImpl.activateStarbucksCardTrue(cardNumber, code);
	}

	//delete all the starbucks records
	@RequestMapping(value="/cards", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllCards(){
		cardServiceImpl.deleteStarbucksCards();
	}



	// ********************************** Orders ****************************************************//


	//get all the Orders api
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	@ResponseBody
	public List<Orders> getAllOrders() {
		return orderServiceImpl.getAllOrders();
	}

	//Submit the order api
	@RequestMapping(value="/order/register", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView createOrder(Orders order,RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		Orders orders = orderServiceImpl.createOrder(order);
		modelAndView.addObject("order", orders);
		modelAndView.setViewName("result");
		orderServiceImpl.createOrder(order);
		return modelAndView;
	}

	


	//delete all the prder records
	@RequestMapping(value="/orders", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllOrders(){
		orderServiceImpl.deleteAllOrders();
	}


	//find the current order api 
	@RequestMapping(value="/findorder", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView findCurrentOrder() {
		ModelAndView modelAndView = new ModelAndView();
		Orders orders = orderServiceImpl.getSpecificOrder();
		modelAndView.addObject("order", orders);
		modelAndView.setViewName("result");
		return modelAndView;
	}


	//find the starbucks card api 
	@RequestMapping(value="/deleteorder", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView deleteActiveOrder(@RequestParam("id") String id,Model model, ModelAndView modelAndView) {
		int number = Integer.parseInt(id);
		orderServiceImpl.deleteActiveOrder(number);
		modelAndView.addObject("successMsg", "Deleted Item succesfully");
		modelAndView.setViewName("success");
		return modelAndView;
	}


	//Submit the order api
	@RequestMapping(value="/order/pay", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView paytheOrder(@RequestParam("cardnumber") String cardNumber, @RequestParam("id") String id,ModelAndView modelAndView) {
		int number = Integer.parseInt(id);
		Cards card = orderServiceImpl.paytheOrders(cardNumber, number);
		if(card != null){
			modelAndView.addObject("successMsg", "Placed the order Succesfully");
			modelAndView.setViewName("success");
		}
		else{
			modelAndView.addObject("errorMsg", "Error in placing the order");
			modelAndView.setViewName("xerror");
		}
		return modelAndView;
	}
}

