package com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
@RequiredArgsConstructor
public class PageController {
    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }
}
