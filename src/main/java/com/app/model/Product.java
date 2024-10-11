package com.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
@Entity
@Data
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
    private Integer category;
}