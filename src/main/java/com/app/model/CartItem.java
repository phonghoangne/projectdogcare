package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cart_id" )
    private Integer cart;
    @Column(name = "product_id")
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
    @Column(name = "customer_id" )
    private Integer customerId;
    @Temporal(TemporalType.TIMESTAMP) // anh xa datetime trong sql thanh date trong java
    private Date createdDate;
    private BigDecimal totalPrice;
    private String status;

}
