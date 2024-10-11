package com.app.model;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Data
@Table(name = "cart")
public class Cart {
//    id int auto_increment primary key,
//    customer_id int,
//    created_date datetime default current_timestamp,
//    total decimal(10,2) default 0.0,
//    foreign key(customer_id) references customer(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "customer_id" )
    private Integer customer;
    @Temporal(TemporalType.TIMESTAMP) // anh xa datetime trong sql thanh date trong java
    private Date createdDate;
    private BigDecimal total;

}
