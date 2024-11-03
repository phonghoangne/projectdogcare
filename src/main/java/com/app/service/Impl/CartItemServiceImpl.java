package com.app.service.Impl;

import com.app.DTO.CartItemDto;
import com.app.Exception.ObjectNotFoundException;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.repository.CartItemRepository;
import com.app.repository.ProductRepository;
import com.app.service.CartItemService;
import com.app.service.ProductService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

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
    public CartItem addProductToCart(Integer productId, Integer customerId, Integer quantity, Locale locale) {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent())
            throw  new ObjectNotFoundException(messageSource.getMessage("product.notfound", null,locale));
        CartItem itemSave = new CartItem();
        itemSave.setProductId(productId);
        itemSave.setPrice(product.get().getPrice());
        itemSave.setCustomerId(customerId);
        itemSave.setQuantity(quantity);
        itemSave.setCreatedDate(new Date());
        itemSave.setStatus("DRA"); // DRA , COM
        //  1 + 1  = 2
        //BigDecimal.multiply = *
        //BigDecimal.add = +
        //BigDecimal.divide = /
        //BigDecimal.subtract = -
        itemSave.setTotal(product.get().getPrice().multiply(BigDecimal.valueOf(quantity)));
        return this.cartItemRepository.save(itemSave);
    }

    @Override
    public List<CartItemDto> getAllByCustomerIdAndStatus(Integer customerId, String status) {

        List<CartItem> resultCart =  this.cartItemRepository.findByCustomerIdAndStatus(customerId,status);

        List<CartItemDto >cartItemDtos = new ArrayList<>();

        for (CartItem itemCart : resultCart) {

            CartItemDto dto = new CartItemDto();
            dto.setId(itemCart.getId());
            dto.setPrice(itemCart.getPrice());
            dto.setStatus(itemCart.getStatus());
            dto.setCreatedDate(itemCart.getCreatedDate());
            dto.setTotalPrice(itemCart.getTotal());
            dto.setQuantity(itemCart.getQuantity());
            Product product = productService.findById(itemCart.getProductId());
            dto.setProductName(product.getProductName());

            cartItemDtos.add(dto);
        }

        return cartItemDtos;
    }

}
