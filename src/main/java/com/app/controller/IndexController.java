package com.app.controller;

import com.app.model.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class IndexController {
    private final MessageSource messageSource;

    private final CommonController commonController;
    @GetMapping("/home")
    public String index(Model model) {
        commonController.getAllProductCategory(model);
        return "index-4";
    }

    @GetMapping("/index")
    public String index1 (Model model, Locale locale)
    {
        model.addAttribute("success",messageSource.getMessage("success",null,locale));
        return "index1";
    }

    @GetMapping("/index2")
    public String index12 (Model model, Locale locale)
    {
        model.addAttribute("success",messageSource.getMessage("success",null,locale));
        return "index2";
    }
}
