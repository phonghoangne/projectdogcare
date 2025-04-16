package com.app.controller;

import com.app.DTO.CartItemDto;
import com.app.model.CartItem;
import com.app.model.Order;
import com.app.model.OrderLine;
import com.app.service.CartItemService;
import com.app.service.CartService;
import com.app.service.OrderLineService;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app/order")
@RequiredArgsConstructor
public class OrderController {

    private final  OrderService orderService;

    private final OrderLineService orderLineService;

    private final  CartItemService cartItemService;

    @GetMapping("/checkout")
    public String getCheckout(@RequestParam Integer customerId, Model model) {
        model.addAttribute("order", new Order());
        List<CartItemDto> cartItems = cartItemService.getAllByCustomerIdAndStatus(customerId, "DRA");
        model.addAttribute("cartItems", cartItems);
        return "Order";
    }


    @PostMapping("/checkout")
    public String checkout(Order order,Model model){
        BigDecimal total = new BigDecimal(0);
        order.setOrderDate(new java.util.Date());
        order = this.orderService.save(order);
        List<CartItemDto> cartItems = cartItemService.getAllByCustomerIdAndStatus(order.getCustomer(), "DRA");

        for (CartItemDto cartItem : cartItems) {
            // tao line cho order
            OrderLine line = new OrderLine();
            line.setOrderId(order.getId());
            line.setProductId(cartItem.getProductId());
            line.setQuantity(cartItem.getQuantity());
            line.setPrice(cartItem.getPrice());
            orderLineService.save(line);
            total = total.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));

            // cap nhat trang thai cua cart item
            CartItem cartItem1 =cartItemService.findById(cartItem.getId());
            cartItem1.setStatus("COM");
            cartItemService.save(cartItem1);
        }
        order.setTotalAmount(total);
        order.setStatus("COM");
        model.addAttribute("success","success");
        return "OrderComplete";
    }

}
