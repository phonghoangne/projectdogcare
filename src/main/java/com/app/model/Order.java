package com.app.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "`order`")
public class Order {
//    id int auto_increment primary key,
//    customer_id int ,
//    order_date datetime default current_timestamp,
//    status varchar(50) default 'pending',
//    total_amount decimal(10,2) not null ,foreign key(customer_id) references customer(id)
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @ManyToOne
        @JoinColumn(name = "customer_id", referencedColumnName = "id")
        private Customer customer;
        private Date orderDate;
        private String status;
        private double totalAmount;

}
