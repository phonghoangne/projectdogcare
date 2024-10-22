package com.app.service;

import com.app.DTO.CustomerLoginRequest;
import com.app.model.User;
import jakarta.mail.MessagingException;

public interface UserService extends BaseModel<User,Integer>{
    User findByUserNameAndPassword(CustomerLoginRequest  request);
    User findByUserName(String username);
    User  findByUserNameAndToken(String userName,String token);
    void forgotPassword(String email) throws MessagingException;
}
