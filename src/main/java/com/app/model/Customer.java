package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "customer")
public class Customer {
//id int auto_increment primary key ,
//first_name varchar(50) not null ,
//last_name varchar(50) not null,
//email varchar(50) unique not null,
//phone varchar(50),
//address text
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
