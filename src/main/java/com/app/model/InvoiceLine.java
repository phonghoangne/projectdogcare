package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "invoice_line")
public class InvoiceLine {
//    id int auto_increment primary key ,
//    invoice_id int ,
//    product_id int ,
//    quantity int not null,
//    price decimal(10,2) not null,
//    foreign key (invoice_id) references invoice(id),
//    foreign key (product_id) references product(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "invoice_id" , referencedColumnName = "id")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
