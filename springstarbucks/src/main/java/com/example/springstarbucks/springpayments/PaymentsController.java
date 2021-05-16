package com.example.springstarbucks.springpayments;

import javax.validation.Valid;

import com.example.springstarbucks.drinks.Drink;
import com.example.springstarbucks.drinks.DrinkParser;
import com.example.springstarbucks.springcybersource.AuthRequest;
import com.example.springstarbucks.springcybersource.AuthResponse;
import com.example.springstarbucks.springcybersource.CaptureRequest;
import com.example.springstarbucks.springcybersource.CaptureResponse;
import com.example.springstarbucks.springcybersource.CyberSourceAPI;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Optional;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


// AUTHENTICATION IMPORT
import com.example.springstarbucks.Authentication.model.User;
import com.example.springstarbucks.Authentication.repository.UserRepository;
import com.example.springstarbucks.Authentication.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequestMapping("/")
public class PaymentsController { 
    

    private static boolean DEBUG = true;
    @Value("${cybersource.apihost}") private String apiHost ;
    @Value("${cybersource.merchantkeyid}") private String merchantKeyId ;
    @Value("${cybersource.merchantsecretkey}") private String merchantsecretKey ;
    @Value("${cybersource.merchantid}") private String merchantId ;

    private CyberSourceAPI api = new CyberSourceAPI();

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

    @Autowired
    private PaymentsCommandRepository repository;

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


    @GetMapping("/pay/{drinkname}")
    public String getAction( @PathVariable("drinkname") String drinkname,
                             @ModelAttribute("command") PaymentsCommand command, 
                             Model model) {

        Drink drink = new DrinkParser(drinkname).setDrink();
		String message = drink.getMessage() + " costs " + drink.getCost();
		model.addAttribute("cost", drink.getCost());
		String image = "/images/" + drinkname.toLowerCase() + ".png";
		model.addAttribute("drink_image",image);
        System.out.println("model in pay " + model);                        

                            
        return "payment" ;

    }                     
        

    @PostMapping("/pay/{drinkname}")
    public String postAction(@PathVariable("drinkname") String drinkname,
                             @Valid @ModelAttribute("command") PaymentsCommand command,  
                             @RequestParam(value="action", required=true) String action,
                             Errors errors, Model model, HttpServletRequest request) {


        Drink drink = new DrinkParser(drinkname).setDrink();
        String message = drink.getMessage() + " costs " + drink.getCost();
        model.addAttribute("cost", drink.getCost());
        String image = "/images/" + drinkname.toLowerCase() + ".png";
        model.addAttribute("drink_image",image);
        System.out.println("model in pay " + model);                 
    
        log.info( "Action: " + action ) ;
        log.info( "Command: " + command ) ;

        CyberSourceAPI.setHost(apiHost);
        CyberSourceAPI.setKey(merchantKeyId);
        CyberSourceAPI.setSecret(merchantsecretKey);
        CyberSourceAPI.setMerchant(merchantId);

        CyberSourceAPI.debugConfig();



        ErrorMessages msgs = new ErrorMessages();

        boolean hasErrors = false;
        if ( command.getFirstname().equals("") ) { hasErrors = true; msgs.add("First Name Required."); }
        if ( command.getLastname().equals("") ) { hasErrors = true; msgs.add("Last Name Required."); }
        if ( command.getAddress().equals("") ) { hasErrors = true; msgs.add("Address Required."); }
        if ( command.getCity().equals("") ) { hasErrors = true; msgs.add("City Required."); }
        if ( command.getState().equals("") ) { hasErrors = true; msgs.add("State Required."); }
        if ( command.getZip().equals("") ) { hasErrors = true; msgs.add("Zip Required."); }
        if ( command.getPhone().equals("") ) { hasErrors = true; msgs.add("Phone Required."); }
        if ( command.getCardnum().equals("") ) { hasErrors = true; msgs.add("Credit Card Number Required."); }
        if ( command.getExpmonth().equals("") ) { hasErrors = true; msgs.add("Credit Card Month Required."); }
        if ( command.getExpyear().equals("") ) { hasErrors = true; msgs.add("Credit Card Year Required."); }
        if ( command.getCvv().equals("") ) { hasErrors = true; msgs.add("Credit Card CVV Required."); }
        if ( command.getEmail().equals("") ) { hasErrors = true; msgs.add("Email Address Required."); }

        if(!command.getZip().matches("\\d{5}")) {
            //System.out.println("Error: Zip code incorrect, should have 5 digits");
            msgs.add("Invalid zip, Your input: " + command.getZip());
            hasErrors = true;
            
        }
        if(!command.getPhone().matches("[(]\\d{3}[)] \\d{3}-\\d{4}")) {
            //System.out.println("Error: phone number incorrect format, should be in the format (###) ###-####");
            msgs.add("Invalid phone, Your input: " + command.getPhone());
            hasErrors = true;
        }
        if(!command.getCardnum().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
            //System.out.println("Error: card number incorrect format, should be in the format ####-####-####");
            msgs.add("Invalid card number format, Your input: " + command.getCardnum());
            hasErrors = true;
        }
        if(!command.getExpyear().matches("\\d{4}")) {
            //System.out.println("Error: Expiration year incorrect, should have 4 digits");
            msgs.add("Invalid card exp year, Your input: " + command.getExpyear());
            hasErrors = true;
        }
        if(!command.getCvv().matches("\\d{3}")) {
            //System.out.println("Error: cvv incorrect, should have 3 digits");
            msgs.add("Invalid card cvv, Your input: " + command.getCvv());
            hasErrors = true;
        }

        if (months.get(command.getExpmonth()) == null)  {
            hasErrors = true;
            msgs.add(  "Invalid Card Expiration Month, Your input: " + command.getExpmonth());
        } 
        if (states.get(command.getState()) == null)  {
            hasErrors = true;
            msgs.add(  "Invalid State, Your input: " + command.getState());
        } 

        if (hasErrors) {
            msgs.print();
            model.addAttribute("messages", msgs.getMessages());
            return "payment";
        }

        int min = 1239871;
        int max = 9999999;
        int random_int = (int) Math.floor(Math.random() * (max-min+1) + min);
        //System.out.println("random int " + random_int); 
        String order_num = Integer.toString(random_int);
        AuthRequest auth = new AuthRequest();
        auth.reference = order_num;
        auth.billToFirstName = command.getFirstname();
        auth.billToLastName = command.getLastname();
        auth.billToAddress = command.getAddress();
        auth.billToCity = command.getCity();
        auth.billToState = command.getState();
        auth.billToZipCode = command.getZip();
        auth.billToPhone = command.getPhone();
        auth.billToEmail = command.getEmail();
        auth.transactionAmount = drink.getCost();
        auth.transactionCurrency = "USD";
        auth.cardNumnber = command.getCardnum();
        auth.cardExpMonth = months.get(command.getExpmonth());
        auth.cardExpYear = command.getExpyear();
        auth.cardCVV = command.getCvv();

       
        auth.cardType = CyberSourceAPI.getCardType(auth.cardNumnber); //typo in the authrequest.java field
        if(auth.cardType.equals("ERROR")) {
            System.out.println("Unsupported Credit Card Type.");
            model.addAttribute("message","Unsupported Credit Card Type." );
            return "payment";
        }
        boolean authValid=true;
        AuthResponse authResponse = new AuthResponse();
        System.out.println("\n\nAuth Request: " + auth.toJson());
        authResponse = api.authorize(auth); //Authorize request using api
        System.out.println("\n\nAuth Response: " + authResponse.toJson());
        if (!authResponse.status.equals("AUTHORIZED")) {
            authValid = false;
            System.out.println("ERROR IN AUTH RESPONSE:" +  authResponse.message);
            model.addAttribute("message", authResponse.message);
            return "payment";
        }

        boolean captureValid = true;
        CaptureRequest capture = new  CaptureRequest();
        CaptureResponse captureResponse = new CaptureResponse();
        if(authValid) {
            capture.reference = order_num;
            capture.paymentId = authResponse.id;
            capture.transactionAmount = drink.getCost();
            capture.transactionCurrency = "USD";
            System.out.println("\n\nCapture Request: " + capture.toJson());
            captureResponse = api.capture(capture);
            System.out.println("\n\nCapture Response: " + captureResponse.toJson());     
            if(!captureResponse.status.equals("PENDING")) {
                captureValid = false;
                System.out.println(captureResponse.message);
                model.addAttribute("message", captureResponse.message);
                return "payment";
            }
        }
        
        if (authValid && captureValid) {
            command.setOrderNumber(order_num);
            command.setTransactionAmount(drink.getCost());
            command.setTransactionCurrency("USD");
            command.setAuthId(authResponse.id);
            command.setAuthStatus(authResponse.status);
            command.setCaptureId(captureResponse.id);
            command.setCaptureStatus(captureResponse.status);

            repository.save(command);

             /* Render View */
            System.out.println("Thank you for your Payment! Your Order Number is: " + order_num);
            model.addAttribute( "message", "Thank You for Your Payment! Your Order Number is: " + order_num ) ;
     
        }

        
    


        return "payment";
    }

    @Autowired
    UserRepository userR;



    @ModelAttribute("loggedInUser")
    public List<User> globalUserObject(@ModelAttribute("user") @Validated UserRegistrationDto userDto, BindingResult result,  Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        if(loggedInUser instanceof AnonymousAuthenticationToken || loggedInUser == null) return new ArrayList<>();


        String email = loggedInUser.getName(); 
        List<User> customerizedUser = userR.findByEmail(email);
        if(!customerizedUser.isEmpty()){
            String username = customerizedUser.get(0).getUsername();
            model.addAttribute("username", username);
        }else{
            List<User> oktaUser = userR.findByUserId(email);
            if(oktaUser.isEmpty()) return new ArrayList<>();
            customerizedUser = oktaUser;
            String username = oktaUser.get(0).getAuthName();
            model.addAttribute("username", username);
        }
        return customerizedUser;
    }

}