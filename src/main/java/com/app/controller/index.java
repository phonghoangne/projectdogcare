package com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class index {
    private final MessageSource messageSource;

    @GetMapping("/home")
    public  String index(Model model, Locale locale)
    {
        model.addAttribute("message", messageSource.getMessage("label.language", null,locale) );
        return "index-4";
    }


}
