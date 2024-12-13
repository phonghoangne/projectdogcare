package com.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;
    private String description;
    private BigDecimal price;
    private String animalType;
    @Column(name = "category_id")
    private Integer categoryId;
    private String image;
}