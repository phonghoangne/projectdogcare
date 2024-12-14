package com.app.controller.admin;

import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping("/list")
    public String listUser() {
        return "admin/user";
    }
}
