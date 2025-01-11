package com.app.controller;

import com.app.model.Cart;
import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.model.User;
import com.app.service.*;
import com.app.utils.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    CartService cartService;
    SessionService sessionService;
    ProductService productService;
    UserService userService;
    InvoiceService invoiceService;
    ProductCategoryService productCategoryService;


    Locale japanLocale = new Locale("ja","JP");
    NumberFormat currenyFormatter = NumberFormat.getCurrencyInstance(japanLocale);
@GetMapping
    public String getCart(Model model){
    return "cart";
}
}
