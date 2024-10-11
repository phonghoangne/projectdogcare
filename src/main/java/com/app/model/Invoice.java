package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Table(name = "invoice")
public class Invoice {
//    id int auto_increment primary key,
//    order_id int,
//    invoice_date datetime default current_timestamp,
//    total_amount decimal(10,2) not null,
//    foreign key (order_id) references `order`(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_id" )
    private Integer order;
    private Date invoiceDate;
    private BigDecimal totalAmount;





}
