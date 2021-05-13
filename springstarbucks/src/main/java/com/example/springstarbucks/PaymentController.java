package com.example.springstarbucks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.example.springstarbucks.drinks.Drink;
import com.example.springstarbucks.drinks.DrinkParser;

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

import lombok.Getter;
import lombok.Setter;



@Controller
public class PaymentController {

    private static Map<String,String> months = new HashMap<>();
    static {
        months.put("January","01");
        months.put("February","02");
        months.put("March","03");
        months.put("April","04");
        months.put("May","05");
        months.put("June","06");
        months.put("July","07");
        months.put("August","08");
        months.put("September","09");
        months.put("October","10");
        months.put("November","11");
        months.put("December","12");
    }

    private static Map<String,String> states = new HashMap<>();
    static {
        states.put("AL","Alabama");
        states.put("AK","Alaska");
        states.put("AZ","Arizona");
        states.put("AR","Arkansas");
        states.put("CA","California");
        states.put("CO","Colorado");
        states.put("CT","Connecticut");
        states.put("DE","Delaware");
        states.put("FL","Florida");
        states.put("GA","Georgia");
        states.put("HI","Hawaii");
        states.put("ID","Idaho");
        states.put("IL","Illinois");
        states.put("IN","Indiana");
        states.put("KS","Kansas");
        states.put("KY","Kentucky");
        states.put("LA","Louisiana");
        states.put("ME","Maine");
        states.put("MD","Maryland");
        states.put("MA","Massachusetts");
        states.put("MI","Michigan");
        states.put("MN","Minnesota");
        states.put("MS","Mississippi");
        states.put("MO","Missouri");
        states.put("MT","Montana");
        states.put("NE","Nebraska");
        states.put("NV","Nevada");
        states.put("NH","New Hampshire");
        states.put("NM","New Mexico");
        states.put("NY","New York");
        states.put("NC","North Carolina");
        states.put("ND","North Dakota");
        states.put("OH","Ohio");
        states.put("OK","Oklahoma");
        states.put("OR","Oregon");
        states.put("PA","Pennsylvania");
        states.put("SC","South Carolina");
        states.put("SD","South Dakota");
        states.put("TN","Tennessee");
        states.put("TX","Texas");
        states.put("UT","Utah");
        states.put("VT","Vermont");
        states.put("VA","Virginia");
        states.put("WA","Washington");
        states.put("WV","West Virginia");
        states.put("WI","Wisconsin");
        states.put("WY","Wyoming");

    }

    @Getter
    @Setter
    class Message {

        private String msg;
        public Message(String m) { msg = m; }

    }

    class ErrorMessages {
        private ArrayList<Message> messages = new ArrayList<Message>();
        public void add(String msg) { messages.add(new Message(msg));}
        public ArrayList<Message> getMessages() {return messages;}
        public void print() {
            for(Message m : messages) {
                System.out.println(m.msg);
            }
        }
    }

    // @GetMapping("/pay/{drinkname}")
    // public String getAction( @PathVariable("drinkname") String drinkname,
    //                          @ModelAttribute("command") PaymentsCommand command, Model model ) {

    //     Drink drink = new DrinkParser(drinkname).setDrink();
	// 	String message = drink.getMessage() + " costs " + drink.getCost();
	// 	model.addAttribute("cost", drink.getCost());
	// 	String image = "/images/" + drinkname.toLowerCase() + ".png";
	// 	model.addAttribute("drink_image",image);
    //     System.out.println("model in pay " + model);

                        
    //     return "payment" ;

    // } 

    // @PostMapping("/pay/{drinkname}")
    // public String postAction(@PathVariable("drinkname") String drinkname,
    //                          @Valid @ModelAttribute("command") PaymentsCommand command,
    //                          @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
    //                          HttpServletRequest request) {
                
    //             Drink drink = new DrinkParser(drinkname).setDrink();
    //             String message = drink.getMessage() + " costs " + drink.getCost();
    //             model.addAttribute("cost", drink.getCost());
    //             String image = "/images/" + drinkname.toLowerCase() + ".png";
    //             model.addAttribute("drink_image",image);
    //             System.out.println("model in pay " + model);


    //             ErrorMessages msgs = new ErrorMessages();

    //             boolean hasErrors = false;
    //             if ( command.getFirstname().equals("") ) { hasErrors = true; msgs.add("First Name Required."); }
    //             if ( command.getLastname().equals("") ) { hasErrors = true; msgs.add("Last Name Required."); }
    //             if ( command.getAddress().equals("") ) { hasErrors = true; msgs.add("Address Required."); }
    //             if ( command.getCity().equals("") ) { hasErrors = true; msgs.add("City Required."); }
    //             if ( command.getState().equals("") ) { hasErrors = true; msgs.add("State Required."); }
    //             if ( command.getZip().equals("") ) { hasErrors = true; msgs.add("Zip Required."); }
    //             if ( command.getPhone().equals("") ) { hasErrors = true; msgs.add("Phone Required."); }
    //             if ( command.getCardnum().equals("") ) { hasErrors = true; msgs.add("Credit Card Number Required."); }
    //             if ( command.getExpmonth().equals("") ) { hasErrors = true; msgs.add("Credit Card Month Required."); }
    //             if ( command.getExpyear().equals("") ) { hasErrors = true; msgs.add("Credit Card Year Required."); }
    //             if ( command.getCvv().equals("") ) { hasErrors = true; msgs.add("Credit Card CVV Required."); }
    //             if ( command.getEmail().equals("") ) { hasErrors = true; msgs.add("Email Address Required."); }
        
    //             if(!command.getZip().matches("\\d{5}")) {
    //                 //System.out.println("Error: Zip code incorrect, should have 5 digits");
    //                 msgs.add("Invalid zip, Your input: " + command.getZip());
    //                 hasErrors = true;
                    
    //             }
    //             if(!command.getPhone().matches("[(]\\d{3}[)] \\d{3}-\\d{4}")) {
    //                 //System.out.println("Error: phone number incorrect format, should be in the format (###) ###-####");
    //                 msgs.add("Invalid phone, Your input: " + command.getPhone());
    //                 hasErrors = true;
    //             }
    //             if(!command.getCardnum().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
    //                 //System.out.println("Error: card number incorrect format, should be in the format ####-####-####");
    //                 msgs.add("Invalid card number format, Your input: " + command.getCardnum());
    //                 hasErrors = true;
    //             }
    //             if(!command.getExpyear().matches("\\d{4}")) {
    //                 //System.out.println("Error: Expiration year incorrect, should have 4 digits");
    //                 msgs.add("Invalid card exp year, Your input: " + command.getExpyear());
    //                 hasErrors = true;
    //             }
    //             if(!command.getCvv().matches("\\d{3}")) {
    //                 //System.out.println("Error: cvv incorrect, should have 3 digits");
    //                 msgs.add("Invalid card cvv, Your input: " + command.getCvv());
    //                 hasErrors = true;
    //             }
        
    //             if (months.get(command.getExpmonth()) == null)  {
    //                 hasErrors = true;
    //                 msgs.add(  "Invalid Card Expiration Month, Your input: " + command.getExpmonth());
    //             } 
    //             if (states.get(command.getState()) == null)  {
    //                 hasErrors = true;
    //                 msgs.add(  "Invalid State, Your input: " + command.getState());
    //             } 
        
    //             if (hasErrors) {
    //                 msgs.print();
    //                 model.addAttribute("messages", msgs.getMessages());
    //                 return "payment";
    //             }
                
        
    //     model.addAttribute("message", "Thank You for Your Payment!");

    //     return "payment";

    // }

}
