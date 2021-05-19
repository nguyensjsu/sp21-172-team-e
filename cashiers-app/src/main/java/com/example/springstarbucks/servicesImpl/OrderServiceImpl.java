package com.example.springstarbucks.servicesImpl;

import java.util.List;

import com.example.springstarbucks.dao.CardRepository;
import com.example.springstarbucks.dao.OrderRepository;
import com.example.springstarbucks.model.Cards;
import com.example.springstarbucks.model.Orders;
import com.example.springstarbucks.services.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements IOrderService{


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CardRepository cardRepository;

    //get all orders
    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }


    //create a new order
    @Override
    public Orders createOrder(Orders order) {
        switch(order.getDrink().toLowerCase().toString()){
            case "americano":{
                if(order.getSize().equalsIgnoreCase("tall")){
                    order.setTotal(2.25);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                else if(order.getSize().equalsIgnoreCase("grande")){
                    order.setTotal(3.25);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                return order;
            }
            case "caffe latte":{
                if(order.getSize().equalsIgnoreCase("tall")){
                    order.setTotal(3.24);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                else if(order.getSize().equalsIgnoreCase("grande")){
                    order.setTotal(4.25);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                return order;
            }
            case "flat white":{
                if(order.getSize().equalsIgnoreCase("tall")){
                    order.setTotal(3.95);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                else if(order.getSize().equalsIgnoreCase("grande")){
                    order.setTotal(4.95);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                return order;
            }
            case "latte macchiato":{
                if(order.getSize().equalsIgnoreCase("tall")){
                    order.setTotal(3.95);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                else if(order.getSize().equalsIgnoreCase("grande")){
                    order.setTotal(4.95);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                return order;
            }
            case "caramel macchiato":{
                if(order.getSize().equalsIgnoreCase("tall")){
                    order.setTotal(3.75);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                else if(order.getSize().equalsIgnoreCase("grande")){
                    order.setTotal(4.75);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                return order;
            }
            case "caffe mocha":{
                if(order.getSize().equalsIgnoreCase("tall")){
                    order.setTotal(3.95);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                else if(order.getSize().equalsIgnoreCase("grande")){
                    order.setTotal(4.95);
                    order.setStatus("Ready for payment");
                    orderRepository.save(order);
                }
                return order;
            }
            default:{
                order.setTotal(0.0);
                order.setStatus("Not Completed !");
                orderRepository.save(order);
                return order;
            }
        }
    }




    //delete all orders
    @Override
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    //get the specific active order
    @Override
    public Orders getSpecificOrder() {
        return orderRepository.findTopByOrderByIdDesc();
    }


    //delete the specific active order
    @Override
    public String deleteActiveOrder(int id) {
        orderRepository.deleteById(id);
        return "Active Order Cleared!";
    }

    //pay the order
    @Override
    public Cards paytheOrders(String cardNumber, int orderId) {
        if(orderRepository.findById(orderId) != null){
            Cards card = cardRepository.findBycardNumber(cardNumber);
            Orders order = orderRepository.findById(orderId);
            if(card != null && order != null){
                card.setBalance(card.getBalance()-order.getTotal()); //set the new value 
                cardRepository.save(card);
                return card;
            }
            else{
                return null;
            }
        }
        else{
            Cards card = cardRepository.findBycardNumber(cardNumber);          
            return card;
        }
        
    }
 
}
