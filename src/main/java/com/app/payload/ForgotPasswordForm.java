package com.app.payload;

import lombok.Data;

@Data
public class ForgotPasswordForm {
    private String email;
    private String username;
}
