package com.app.repository;

import com.app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsernameAndPassword(String userName, String psw);
    Optional<User>findByUsername(String username);
    Optional<User>findByUsernameAndToken(String username,String token);
    Optional<User>findByEmail(String email);



}
