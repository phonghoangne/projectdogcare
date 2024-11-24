package com.app.controller;

import com.app.model.ProductCategory;
import com.app.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommonController {

    private final ProductCategoryService productCategoryService;

    public void getAllProductCategory(Model model)
    {
        List<ProductCategory> listCategory = productCategoryService.findAll();
        model.addAttribute("listProductCategory",listCategory);
    }



}
