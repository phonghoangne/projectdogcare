package com.app.service.Impl;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final  UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User integer) {
         return userRepository.save(integer);
    }

    @Override
    public void delete(Integer integer) {
        User userDelete = userRepository.findById(integer ).
                orElseThrow(()-> new RuntimeException("User not found"));
        userRepository.delete(userDelete);

    }

    @Override
    public User findById(Integer integer) {
        return userRepository.findById(integer).get();
    }
}
