package com.app.controller;

import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import com.app.service.ProductCategoryService;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/app/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductCategoryService productCategoryService;

    private final CommonController commonController;

    @GetMapping("")
    public String getAllProduct(@ModelAttribute ProductRequest request, Model model)
    {
        GlobalResponsePagination result = productService.getAll(request);
        model.addAttribute("dataProduct",result);
        commonController.getAllProductCategory(model);
        return "product";
    }


}
