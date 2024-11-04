package com.app.service;

import com.app.DTO.CartItemDto;
import com.app.model.CartItem;

import java.util.List;
import java.util.Locale;

public interface CartItemService extends BaseModel<CartItem,Integer> {
    CartItem addProductToCart(Integer productId, Integer customerId, Integer quantity, Locale locale);
   List<CartItemDto> getAllByCustomerIdAndStatus(Integer customerId, String status);
}
