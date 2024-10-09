package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "payment_line")
public class PaymentLine {
//    id int auto_increment primary key,
//    payment_id int ,
//    amount decimal(10,2) not null ,
//    payment_date datetime default current_timestamp,
//    foreign key (payment_id) references payment(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "payment_id",referencedColumnName = "id")
    private Payment payment;
    private double amount;
    private Date paymentDate;

}
