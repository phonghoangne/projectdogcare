package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_category")
public class ProductCategory {
//    id int auto_increment primary key,
//    category_name varchar(50) not null);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;

}
