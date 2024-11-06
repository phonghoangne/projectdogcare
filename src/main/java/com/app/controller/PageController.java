package com.app.controller;

import com.app.DTO.CartItemDto;
import com.app.model.CartItem;
import com.app.model.Order;
import com.app.model.OrderLine;
import com.app.service.CartItemService;
import com.app.service.OrderLineService;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/page")
@RequiredArgsConstructor
public class PageController {
    @Autowired
    private OrderService orderService;

    private final OrderLineService orderLineService;


    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/contact")
    public String getCheckout(@RequestParam Integer customerId, Model model) {
        return "contact";
    }



}
