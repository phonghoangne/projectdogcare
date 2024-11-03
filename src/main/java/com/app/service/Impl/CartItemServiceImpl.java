package com.app.service.Impl;

import com.app.Exception.ObjectNotFoundException;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.repository.CartItemRepository;
import com.app.repository.ProductRepository;
import com.app.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final MessageSource messageSource;


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

    @Override
    public CartItem addProductToCart(Integer productId, Integer customerId, Integer quantity ,Locale locale) {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent())
            throw new ObjectNotFoundException(messageSource.getMessage("product.notfound",null,locale));
        CartItem itemSave = new CartItem();
        itemSave.setProductId(productId);
        itemSave.setPrice(product.get().getPrice());
        itemSave.setCustomerId(customerId);
        itemSave.setQuantity(quantity);
        itemSave.setCreatedDate(new Date());
        itemSave.setTotalPrice(product.get().getPrice().multiply(BigDecimal.valueOf(quantity)));
        itemSave.setStatus("DRA");
        //multiply phep nhan
        // add +
        // divide = /
        // subtract = -
        return this.cartItemRepository.save(itemSave);

    }

    @Override
    public List<CartItem> getAllByCustomerIdAndStatus(Integer customerId, String status) {
        return this.cartItemRepository.findByCustomerIdAndStatus(customerId,status);
    }
}
