package com.app.DTO;

import lombok.Data;

@Data
public class CustomerRegisterRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
