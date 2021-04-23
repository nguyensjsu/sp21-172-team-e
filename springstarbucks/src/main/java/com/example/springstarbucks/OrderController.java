package com.example.springstarbucks;

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


import com.example.springstarbucks.model.*;
import java.util.List;

@Controller
public class OrderController {

	@Autowired
	//get card service?

	@Autowired
	OrderService OrderService;
    
    //*To edit to match new file organization 

    //get all orders
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	@ResponseBody
	public List<StarbucksOrder> all() {
		return OrderService.all();
	}

	//Create new order
	@RequestMapping(value="/order/register", method=RequestMethod.POST)
	@ResponseBody
	StarbucksOrder newOrder(@PathVariable String regid, @RequestBody StarbucksOrder order){
		return OrderService.newOrder(regid, order);
	}


	//delete all orders
	@RequestMapping(value="/orders", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAll(){
		OrderService.deleteAll();
	}


	//get order details
	@RequestMapping(value="/findorder", method = RequestMethod.GET)
	@ResponseBody
	StarbucksOrder getActiveOrder(@PathVariable String regid, HttpServletResponse response) {
		return OrderService.getSpecificOrder(regid, response);
	}


	//clear active order
	@RequestMapping(value="/deleteorder", method = RequestMethod.DELETE)
	@ResponseBody
	String deleteActiveOrder(@PathVariable String regid) {
		return OrderService.deleteActiveOrder(regid);
	}


	//Process Order payment
	@RequestMapping(value="/order/pay", method=RequestMethod.POST)
	@ResponseBody
	public StarbucksCard processOrder(@PathVariable String regid, @PathVariable String cardnum) {
		return OrderService.ProcessOrder(regid, cardnum);
	}

	//TODO: Verify that all of these Request mappings work
    //If not, return to previous getmapping methods 
}
