package com.app.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    Integer id;
    @Size(max = 50, message = "Product name cannot exceed 50 characters")
    @NotNull(message = "Product name not null")
    String productName;
    String description;
    BigDecimal price;
    String animalType;
    @NotNull(message = "Category ID is required")
    Integer categoryId;
    String image;
    String categoryName;
}
