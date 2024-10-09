package com.app.model;

import jakarta.persistence.*;
        import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_line")
public class OrderLine {
    //    id int auto_increment primary key ,
//    order_id int ,
//    product_id int ,
//    quantity int not null,
//    price decimal(10,2) not null,
//    foreign key (order_id) references `order`(id),
//    foreign key (product_id) references product(id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private Integer  quantity;
    private BigDecimal price;
}
