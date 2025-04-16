package com.app.service.Impl;

import com.app.Exception.ObjectNotFoundException;
import com.app.model.Cart;
import com.app.model.Invoice;
import com.app.model.Product;
import com.app.model.User;
import com.app.repository.CartRepository;
import com.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Override
    public Cart findById(Integer integer) {
        return cartRepository.findById(integer).orElseThrow(() -> new ObjectNotFoundException("khong the tim thay gio hang "+ integer));
    }

    private final CartRepository cartRepository;
    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll() ;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart update(Cart id) {
        return cartRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
        Cart cart = cartRepository.findById(integer).
                orElseThrow(()->new RuntimeException("Cart not found"));
        cartRepository.delete(cart);
    }




    @Override
    public Integer findMaxId() {
        return cartRepository.findMaxId();
    }

}
