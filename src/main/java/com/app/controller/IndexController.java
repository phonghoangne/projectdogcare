package com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class IndexController {
    private final MessageSource messageSource;

    private final CommonController commonController;
    @GetMapping("/home")
    public  String index(Model model)
    {
        commonController.getAllProductCategory(model);
        return "index-4";
    }



}
