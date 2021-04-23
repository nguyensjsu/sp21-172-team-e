//package com.example.springstarbucks.orderapi;
// package com.example.springstarbucks;

// import java.util.List;
// import java.util.HashMap;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

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

// @RestController
// public class OrderController{
//     private final OrderRepository orderRepo; 

//     @Autowired
//     private final CardRepository cardRepo; 

//     /* Constructor */ 
//     OrderController(OrderRepository repository){
//         this.repository = repository; 
//     }

//     /* Message for Status */ 
//     class Message{
//         private String status; 

//         public String getStatus(){
//             return status; 
//         }
        
//         public void setStatus(String msg){
//             status = msg; 
//         }
//     }

//     /* Show all orders */ 
//     @GetMapping("/orders")
//     List<StarbucksOrder> all(){
//         return orderRepo.findAll(); 
//     }

//     /* Delete all orders */ 
//     @DeleteMapping("/orders")
//     Message deleteAll(){
//         orderRepo.deleteAllInBatch(); 
//         orders.clear(); 
//         Message msg = new Message(); 
//         msg.setStatus("All orders have been cleared."); 
//         return msg; 
//     }
//     /* Get Details of an Order */
//     @GetMapping("/order/register/{regid}") 
//     StarbucksOrder getActiveOrder(@PathVariable String regid, HttpServletResponse response) {
//         StarbucksOrder active = orders.get(regid);
//         if (active != null) {
//             return active;
//         } else {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
//         }
//     }

//     /* Clear Active Order */
//     @DeleteMapping("/order/register/{regid}")
//     Message deleteActiveOrder(@PathVariable String regid) {
//         StarbucksOrder active = orders.get(regid);
//         if (active != null) {
//             orders. remove(regid);
//             Message msg = new Message();
//             msg.setStatus("Active Order Cleared!"); 
//             return msg; 
//         } else {
//             throw new ResponseStatusException(HttpStatus .BAD_REQUEST, "Order Not Found!");
//         }
//     }

//     /* Process payment for the "active" Order. */
//     @PostMapping("/order/register/{regid}/pay/{cardnum}")
//     StarbucksCard processOrder(@PathVariable String regid, @PathVariable String cardnum ) {
//         System.out.println( "Pay for Order: Reg ID =" + regid + " Using Card = " + cardnum ) ;
//         StarbucksOrder active = orders.get(regid);
//         if (active == null) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
//         }
//         if ( cardnum.equals("") ) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Number Not Provided!");
//         }
//         if ( active.getStatus().startsWith("Paid with-Card") ) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear Paid Active Order!");
//         }

//         StarbucksCard card = cardRepository.findByCardNumber(cardnum) ; 
//         if ( card == null) {
//             throw new ResponseStatusException(HttpStatus .BAD_REQUEST, "Card Not Foundi");
//         }
//         if ( !card.isActivated() ) {
//             throw new ResponseStatusException(HttpStatus .BAD_REQUEST, "Card Not Activated.");
//         }

//         double price = active.getTotal() ;
//         double balance = card.getBalance() ;
//         if ( balance - price < 0) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds on Card."); 
//         }
//         double new_balance = balance - price ;
//         card.setBalance( new_balance ) ;
//         String status = "Paid with Card: " + cardnum + " Balance: $" + new_balance + "." ;
//         active.setStatus( status ) ;
//         cardRepository.save( card ) ;
//         repository.save( active ) ;
//         return card ; 
//     }

//     /*Create new order*/
//     @PostMapping("/order/register/{regid}")
//     @ResponseStatus(HttpStatus.CREATED)
//     StarbucksOrder newOrder(@PathVariable String regid, @RequestBody StarbucksOrder order){
//         //TODO: Set error codes for inactive order, etc 
//         // set price
//         double price = 0.0;
//         switch (order.getDrink()) {
//         case "Latte":
//             switch (order.getSize()) {
//             case "Tall":
//                 price = 3.25;
//                 break;
//             case "Grande":
//                 price = 3.95;
//                 break;
//             case "Venti":
//             case "Your Own Cup":
//                 price = 4.25;
//                 break;
//             default:
//                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
//             }
//             break;
//         case "Frappuccino":
//             switch (order.getSize()) {
//             case "Tall":
//                 price = 4.75;
//                 break;
//             case "Grande":
//                 price = 5.25;
//                 break;
//             case "Venti":
//             case "Your Own Cup":
//                 price = 5.75;
//                 break;
//             default:
//                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
//             }
//             break;
//         case "Fresh Coffee":
//             switch (order.getSize()) {
//             case "Tall":
//                 price = 1.95;
//                 break;
//             case "Grande":
//                 price = 2.45;
//                 break;
//             case "Venti":
//             case "Your Own Cup":
//                 price = 2.75;
//                 break;
//             default:
//                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
//             }
//             break;
//         case "Cappuccino":
//             switch (order.getSize()) {
//                 case "Tall":
//                     price = 3.25;
//                     break;
//                 case "Grande":
//                     price = 3.95;
//                     break;
//                 case "Venti":
//                 case "Your Own Cup":
//                     price = 4.25;
//                     break;
//                 default:
//                     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
//                 }
//                 break;
//             default:
//                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Drink!"); 
//             }
//             double tax = 0.0725;
//             double total = price + (price * tax);
//             double scale = Math.pow(10, 2);
//             double rounded = Math.round(total * scale) / scale;
//             order.setTotal(rounded);

//             order.setStatus("Ready for Payment.");
//             StarbucksOrder new_order = repository.save(order);
//             orders.put(regid, new_order);

//             return new_order;
//         }
// }