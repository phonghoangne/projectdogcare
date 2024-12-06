package com.app.controller;

import com.app.DTO.CustomerLoginRequest;
import com.app.DTO.CustomerRegisterRequest;
import com.app.payload.ForgotPasswordForm;
import com.app.model.User;
import com.app.service.UserService;
import com.app.utils.CookiesService;
import com.app.utils.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/app/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    private final SessionService sessionService;

    private  final CookiesService cookiesService;

    private final PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String getLogin(Model model, @ModelAttribute CustomerLoginRequest rq)
    {
        String userName = cookiesService.getValue("userNameCookie");
        if(userName!= null )
        {
            User userCookie = userService.findByUserNameAndToken(userName, cookiesService.getValue("tokenCookie"));
            CustomerLoginRequest cookieRequest = new CustomerLoginRequest();
            cookieRequest.setPassword(userCookie.getPassword());
            cookieRequest.setUsername(userCookie.getUsername());
            cookieRequest.setRemember(true);
            model.addAttribute("accountLogin",cookieRequest);
        }else{
            model.addAttribute("accountLogin",new CustomerLoginRequest()); // gan 1 doi tuong rong de nguoi dung tu nhap thong tin
        }
        return "login";
    }
    @PostMapping  ("/login")
        String postLogin(Model model,@ModelAttribute(name = "accountLogin") CustomerLoginRequest rq,HttpServletRequest  rqS, HttpServletResponse response)
    {
        try{
            User userCheckLogin = userService.findByUserNameAndPassword(rq);
            userCheckLogin.setToken(UUID.randomUUID().toString());
            userService.save(userCheckLogin);
            model.addAttribute("message","Dang nhap thanh cong !");
            if(rq.isRemember())
            {
                cookiesService.add("userNameCookie",userCheckLogin.getUsername(),30);
                cookiesService.add("tokenCookie",userCheckLogin.getToken(),30);
            }
            sessionService.set("userLogin",userCheckLogin);
        }catch (Exception e)
        {
            e.printStackTrace();
            model.addAttribute("errorMessage",e.getMessage());
        }

        return "login";
    }
    @GetMapping("/register")
    public String getRegister(Model model ){
        model.addAttribute("accountRegister", new CustomerRegisterRequest());
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(Model model ,@ModelAttribute(name = "accountRegister") CustomerRegisterRequest rq){
        try{
            if(userService.findByUserName(rq.getUsername())!=null){
                throw new RuntimeException("tai khoan da ton tai");
            }
            User newUser = new User();
            newUser.setUsername(rq.getUsername());
            newUser.setPassword(passwordEncoder.encode(rq.getPassword()));
            newUser.setEmail(rq.getEmail());
            newUser.setPhoneNumber(rq.getPhoneNumber());

            userService.save(newUser);
            model.addAttribute("message", "Dang ki thanh cong !");

        }catch(Exception e){
            model.addAttribute("errorMessage",e.getMessage());
        }
        return "register";


    }
    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request,HttpServletResponse respone, Model model){
        request.getSession().invalidate();

        cookiesService.remove("userNameCookie");
        cookiesService.remove("passwordCookie");

        model.addAttribute("message","Da dang xuat thanh cong");

        return "redirect:/app/account/login";

    }
    @GetMapping("/forgot-password")
    public String showForgotPassword(Model model){

        model.addAttribute("forgotPasswordForm" , new ForgotPasswordForm());
        return "forgot-password";
    }
    //    @RequestParam
//    @PathVariable
//    @ModelAttribute
//    @RequestBody
    @PostMapping("/forgot-password")
    public String processForgotPassword(@ModelAttribute("forgotPassword") ForgotPasswordForm form , Model model){
        String email = form.getEmail();
        try{
            userService.forgotPassword(email);
            model.addAttribute("succesMessage","mat khau da duoc gui toi email cua ban ");
        } catch (Exception e){
            model.addAttribute("errorMessage","Khong the gui email , hay thu lai !!!");
        }
        return "forgot-password";
    }



}
