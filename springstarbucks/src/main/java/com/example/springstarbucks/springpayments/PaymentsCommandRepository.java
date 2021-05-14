package com.example.springstarbucks.springpayments;
import org.springframework.data.jpa.repository.JpaRepository;

interface PaymentsCommandRepository extends  JpaRepository<PaymentsCommand,Long> {
    
}