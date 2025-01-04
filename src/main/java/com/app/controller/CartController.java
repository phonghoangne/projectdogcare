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
    @GetMapping("/view")
    public String getCart(Model model, RedirectAttributes redirectAttributes ) {
        Integer account_id = sessionService.get("account_id");
        if(account_id == null) {
            account_id = 0;
        }
        User userSessionId = userService.findById(account_id).orElse(null);
        if (userSessionId == null) {
            System.out.println("Model null ");
            redirectAttributes.addFlashAttribute("messNotificationLogin","Vui long dag nhap de dat hang ");
            return "redirect:/app/home";
        }
        //
        List<ProductCategory> navCategory = productCategoryService.findAll();
        model.addAttribute("navCategory", navCategory);
        Page<Product> productContentSeen = productService.findAll(PageRequest.of(0, 10));
        model.addAttribute("productViewed",productContentSeen.getContent());
        return "cartItem";
    }
//@GetMapping("/add")
//    public String addCart(Model model , @RequestParam("productId") Integer Id , RedirectAttributes redirectAttributes,@RequestParam(value = "quantity",required = false) Integer quantity ) {
//        Integer account_id = sessionService.get("account_id");
//        if(account_id == null) {
//            account_id = 0;
//        }
//    Optional<Product> productAdd = productService.findById(Id);
//        User userAdd = userService.findById(account_id).orElse(null);
//        if (userAdd == null) {
//            System.out.println("Model null ");
//            redirectAttributes.addFlashAttribute("messNotificationLogin","Vui long dag nhap de dat hang ");
//            return "redirect:/app/home";
//        }
//        if (cartService.findMaxId() == null){
//            cartService.addItem(new Cart(1,quantity,productAdd.get(),userAdd));
//
//        }
//}
}
