package com.app.DTO;

import lombok.Data;

@Data

public class CustomerLoginRequest {
    private String username;
    private String password;
    private boolean remember = false;


    @Override
    public String toString() {
        return "customerLoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                '}';
    }
}
