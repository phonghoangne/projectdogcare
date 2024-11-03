package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
    @Column(name = "product_id")
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
    @Column(name = "customer_id" )
    private Integer customerId;
    @Temporal(TemporalType.TIMESTAMP) // anh xa datetime trong sql thanh date trong java
    private Date createdDate;
    private BigDecimal total;//..
    private String status;
}
