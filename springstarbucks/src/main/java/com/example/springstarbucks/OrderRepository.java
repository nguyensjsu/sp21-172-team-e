package com.example.springstarbucks;

import org.springframework.data.jpa.repository.JpaRepository; 

interface OrderRepository extends JpaRepository<StarbucksOrder, Long>{
    StarbucksOrder findById(int id);
    StarbucksOrder deleteById(int id);
}