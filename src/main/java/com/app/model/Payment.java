package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "payment")
public class Payment {
//    id int auto_increment primary key,
//    invoice_id int ,
//    payment_date datetime default current_timestamp,
//    amount decimal(10,2) not null,
//    payment_method varchar(50),
//    foreign key (invoice_id) references invoice(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "invoice_id",referencedColumnName = "id")
    private Invoice invoice;
    private Date paymentDate;
    private BigDecimal amount;
    private String paymentMethod;
}
