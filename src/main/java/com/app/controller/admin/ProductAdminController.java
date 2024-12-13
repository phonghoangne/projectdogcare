package com.app.controller.admin;

import com.app.model.Product;
import com.app.service.ProductCategoryService;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-admin")
@RequiredArgsConstructor
public class ProductAdminController {
         private final ProductService productService;
         private final ProductCategoryService productCategoryService;
         @RequestMapping("/list")

            public String listUser(Model model){

                model.addAttribute("products", productService.getAllProductDto());
                model.addAttribute("productSave",new Product());
                model.addAttribute("productCategory",productCategoryService.findAll());
                return "admin/product";
            }
}
