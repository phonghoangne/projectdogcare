package com.app.service.Impl;

import com.app.DTO.CustomerLoginRequest;
import com.app.model.Invoice;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

// PetShopException -- runtime
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

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
        User userDelete = userRepository.findById(integer).
                orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userDelete);

    }

    @Override
    public Optional<User> findById(Integer Id) {
        return userRepository.findById(Id);
    }


    @Override
    public User findByUserNameAndPassword(CustomerLoginRequest request) {

        User userCheck = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Tài khoản hoặc mật khẩu không chính xác !"));
        if(passwordEncoder.matches(request.getPassword(),userCheck.getPassword()))
            return userCheck;
        throw new RuntimeException("Tai khoan hoac mat khau khong chinh xac");

    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByUserNameAndToken(String userName, String token) {
        return userRepository.findByUsernameAndToken(userName,token).orElseThrow(()->new RuntimeException("\"khong tim thay tai khoan\""));

    }


    @Override
    public void forgotPassword(String email) throws MessagingException {
        String password = generateRandomString(7);
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Khong tim thay email"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(),"Cap lai mat khau","mau khau cua ban la: " + password,null);

    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }



    public  String  generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
