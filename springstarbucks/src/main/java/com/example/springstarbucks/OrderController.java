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
	CardServiceImpl cardServiceImpl;

	@Autowired
	OrderServiceImpl orderServiceImpl;
    
    //*To edit to match new file organization 

	// /* Constructor */ 
    // public StarbucksOrderController(StarbucksOrderRepository repository){
    //     this.repository = repository; 
    // }

    // /* Message for Status */ 
    // class Message{
    //     private String status; 

    //     public String getStatus(){
    //         return status; 
    //     }
        
    //     public void setStatus(String msg){
    //         status = msg; 
    //     }
    // }

    // @GetMapping("/orders")
    // List<StarbucksOrder> all(){
    //     return orderRepo.findAll(); 
    // }

    // @DeleteMapping("/orders")
    // Message deleteAll(){
    //     orderRepo.deleteAllInBatch(); 
    //     orders.clear(); 
    //     Message msg = new Message(); 
    //     msg.setStatus("All orders have been cleared."); 
    //     return msg; 
    // }
}
