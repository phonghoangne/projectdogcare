package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cart_item")
public class CartItem {
//    id int auto_increment primary key,
//    cart_id int ,
//    product_id int,
//    quantity int not null,
//    price decimal(10,2) not null,
//    foreign key(cart_id) references cart(id),
//    foreign key(product_id) references product(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cart_id" )
    private Integer cart;
    @Column(name = "product_id")
    private Integer product;
    private Integer quantity;
    private BigDecimal price;

}
