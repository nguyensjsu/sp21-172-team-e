package com.example.springstarbucks.services;

import java.util.List;

import com.example.springstarbucks.model.Cards;
import com.example.springstarbucks.model.Orders;

public interface IOrderService {


    List<Orders> getAllOrders();
    Orders createOrder(Orders order);
    Orders getSpecificOrder();
    String deleteActiveOrder(int id);
    void deleteAllOrders();
    Cards paytheOrders(String cardNumber, int orderId);
    
}
