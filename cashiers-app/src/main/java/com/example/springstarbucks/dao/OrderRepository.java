package com.example.springstarbucks.dao;


import com.example.springstarbucks.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
    Orders findById(int id);
    Orders deleteById(int id);
    Orders findTopByOrderByIdDesc();
}