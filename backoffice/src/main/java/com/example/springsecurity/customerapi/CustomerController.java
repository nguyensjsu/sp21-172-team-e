package com.example.backoffice.customerapi;

import com.example.backoffice.customerapi.Customer;

import java.util.List;
import java.util.Random;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.Errors;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication; //for getting logged in customer info?
                                                        //TODO: Remove?
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@Controller
@RequestMapping("/backoffice")
class CustomerController{
    @Autowired
    private final CustomerRepository repository; 

    CustomerController(CustomerRepository repository){
        this.repository = repository; 
    }

    @GetMapping
    public String getAction( @ModelAttribute("command") Customer customer, 
                            Model model) {
        model.addAttribute("customer", customer);
        return "backoffice" ; //page to return to
    }

    /*
    @GetMapping 
    public String viewCustomers(){

    }
    */

    @PostMapping
    public String postAction(@Valid @ModelAttribute("customer") Customer customer,  
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) {

        Customer currentCustomer = repository.findByEmail(customer.getEmail()); //get email from form and use it to find the customer
        //model.addAttribute("CurrentCustomer", currentCustomer);

        model.addAttribute( "message", "Message posted!" ) ;

        return "creditcards";
    }

    /*

    @RequestMapping(method=RequestMethod.GET)
    public String rewardsForm(Model model) {
        model.addAttribute("customer", new Customer());
        //model.addAttribute("customer", new Customer());
        return "customer";
    }

    //@PostMapping("/add")
    @RequestMapping(method=RequestMethod.POST)
    public String rewardsSubmit(@ModelAttribute("customer") Customer customer, Model model) {
      model.addAttribute("customer", customer);
      model.addAttribute("email", email);
      return "result";
    }
    */

    /*
    @PostMapping("/add")
    Message addPoints(@PathVariable String email, @PathVariable String points){
        Customer customer = repository.findByEmail(email);
        int rewards = Integer.parseInt(points); //get points to add 
        customer.setRewards(customer.getRewards()+rewards);
        repository.save(customer);
        Message msg = new Message(); 
        msg.setStatus("New points : " + customer.getRewards());
        return msg;

        //TODO: Add ResponseStatusException 
    }
    */
}