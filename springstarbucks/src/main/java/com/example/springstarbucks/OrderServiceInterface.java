// package com.example.springstarbucks;

// import java.util.List;

// //TODO: Check need for the imports below 

// import org.springframework.web.bind.annotation.DeleteMapping; 
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping; 
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseStatus; 
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;
// import org.springframework.beans.factory.annotation.Autowired; 
// import org.springframework.http.HttpStatus; 
// import org.springframework.lang.Nullable; 

// import com.example.springstarbucks.StarbucksOrder;

// public interface OrderServiceInterface {

//     //Get all orders
//         List<StarbucksOrder> all(); 

//     //Create new order
//         StarbucksOrder newOrder(@PathVariable String regid, @RequestBody StarbucksOrder order);
    
//     //Get order details
//         StarbucksOrder getActiveOrder(@PathVariable String regid, HttpServletResponse response);
    
//     //Clear active order
//         String deleteActiveOrder(@PathVariable String regid);

//     //Delete all orders
//         void deleteAll();

//     //Process order payment
//         //StarbucksCard processOrder(@PathVariable String regid, @PathVariable String cardnum);
    
// }