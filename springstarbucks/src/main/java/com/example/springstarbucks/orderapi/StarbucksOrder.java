package com.example.springstarbucks.orderapi;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(indexes=@Index(name = "altIndex", columnList = "id", unique = true))
@Data
@RequiredArgsConstructor
public class StarbucksOrder {
    private @Id @GeneratedValue Long id;

    private double total;
    private String status;
    @Column(nullable = false) private String size;
    @Column(nullable = false) private String drink;
    @Column(nullable = false) private String milk;


}
