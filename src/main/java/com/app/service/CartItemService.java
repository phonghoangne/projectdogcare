package com.app.service;

import com.app.DTO.CartItemDto;
import com.app.model.CartItem;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface CartItemService extends BaseModel<CartItem,Integer> {
    CartItem addProductToCart(Integer productId, Integer customerId, Integer quantity, Locale locale);
   List<CartItemDto> getAllByCustomerIdAndStatus(Integer customerId, String status);
   void clearCart(Integer customerId);
   List<CartItem> findByCustomerId(Integer customerId);
   void removeProductFromCart(Integer productId, Integer customerId);
   Optional<CartItem> findByProductIdAndCustomerId(Integer productId, Integer customerId);
     CartItem updateCartItem(Integer cartId, Integer quantity);
     void checkoutCart(Integer customerId);
     }


