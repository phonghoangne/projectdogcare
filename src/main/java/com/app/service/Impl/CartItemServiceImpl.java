package com.app.service.Impl;

import com.app.model.CartItem;
import com.app.repository.CartItemRepository;
import com.app.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem save(CartItem cartitem) {
        return cartItemRepository.save(cartitem);
    }

    @Override
    public CartItem update(CartItem id) {
        return cartItemRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
        CartItem cartItem = cartItemRepository.findById(integer).
                orElseThrow(()->new RuntimeException("cartItem is not found"));
        cartItemRepository.delete(cartItem);

    }

    @Override
    public CartItem findById(Integer integer) {
        return cartItemRepository.findById(integer).get();
    }
}
