package com.example.backoffice.customerapi;

import com.example.backoffice.customerapi.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByEmail(String email);
}
