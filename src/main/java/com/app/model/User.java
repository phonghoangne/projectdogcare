package com.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
//    id int  primary key,
//    username varchar(52),
//    password varchar(50),
//    role varchar (50) default 'admin'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", nullable = false, length = 52)
    private String username;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    private String role = "Admin";
}
