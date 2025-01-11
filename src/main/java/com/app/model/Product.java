package com.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 50, message = "Product name cannot exceed 50 characters")
    private String productName;
    private String description;
    private BigDecimal price;
    private String animalType;
    @Column(name = "category_id")
    @NotNull(message = "Category ID is required")
    private Integer categoryId;
    private String image;
}