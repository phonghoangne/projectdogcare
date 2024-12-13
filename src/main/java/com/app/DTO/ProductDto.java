package com.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    Integer id;
    String productName;
    String description;
    BigDecimal price;
    String animalType;
    Integer categoryId;
    String image;

    String categoryName;
}
