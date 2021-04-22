package com.example.springstarbucks;

import java.util.Objects;
import javax.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/* https://spring.io/guides/tutorials/rest/ */

@Entity

@Data
@RequiredArgsConstructor
class StarbucksOrder {

    private @Id @GeneratedValue Long id;
    private String drink ;
    private String milk ;
    private String size ;
    private double total ; 
    private String status ;

} 