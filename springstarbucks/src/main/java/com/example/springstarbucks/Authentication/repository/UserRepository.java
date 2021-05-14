package com.example.springstarbucks.Authentication.repository;

import java.util.List;

import com.example.springstarbucks.Authentication.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByEmail(String email);
    List<User> findByUserId(String userId);
    List<User> findByToken(String token);
}
