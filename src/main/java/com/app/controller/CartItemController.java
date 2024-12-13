package com.app.controller;

import com.app.DTO.CartItemDto;
import com.app.model.CartItem;
import com.app.service.CartItemService;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/app/cart")
@RequiredArgsConstructor

public class CartItemController {
    private final CartItemService cartItemService;
    private final ProductService productService;

    @GetMapping("/view")
    public String getCart(Model model, @RequestParam Integer customerId) {
        try {
            List<CartItemDto> cartItemDtos = cartItemService.getAllByCustomerIdAndStatus(customerId, "DRA");
            model.addAttribute("listCart", cartItemDtos);
        } catch (Exception e) {
        }
        return "cartItem";
    }
    @GetMapping("/add")
    public String addCart(Model model, @RequestParam Integer productId, @RequestParam  Integer customerId, Locale locale){
        CartItem cartItem = cartItemService.addProductToCart(productId,customerId,1,locale);
        model.addAttribute("succss","success");
        return "redirect:/app/cart/view?customerId="+customerId;
    }

    @GetMapping("/delete")
    public  String deleteCartItem(Model model,@RequestParam Integer cartId,@RequestParam Integer customerId,Locale locale){
        cartItemService.delete(cartId);
        model.addAttribute("succss","success");
        return "redirect:/app/cart/view?customerId="+customerId;

    }
    @GetMapping("/cartsimple")
    public String cartSim(){
        return "cart1";
    }
}