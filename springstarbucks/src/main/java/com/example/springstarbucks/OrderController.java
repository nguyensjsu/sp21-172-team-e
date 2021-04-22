package com.example.springstarbucks;

import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.lang.Nullable; 

@RestController
public class OrderController{
    private final OrderRepository orderRepo; 

    @Autowired
    private final CardRepository cardRepo; 

    /*Constructor*/ 
    public StarbucksOrderController(StarbucksOrderRepository repository){
        this.repository = repository; 
    }

    /*Message for Status*/ 
    class Message{
        private String status; 

        public String getStatus(){
            return status; 
        }
        
        public void setStatus(String msg){
            status = msg; 
        }
    }

}