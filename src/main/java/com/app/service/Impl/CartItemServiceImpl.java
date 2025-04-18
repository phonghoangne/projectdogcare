package com.app.service.Impl;

import com.app.DTO.CartItemDto;
import com.app.Exception.ObjectNotFoundException;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.repository.CartItemRepository;
import com.app.repository.ProductRepository;
import com.app.service.CartItemService;
import com.app.service.ProductService;
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
    private final MessageSource messageSource;
    private final ProductService productService;



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
        return cartItemRepository.findById(integer).orElseThrow(() -> new ObjectNotFoundException("Khong the tim thay cartitem"+ integer));
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
        itemSave.setStatus("DRA");
        itemSave.setTotalPrice(product.get().getPrice().multiply(BigDecimal.valueOf(quantity)));
        //multiply phep nhan
        // add +
        // divide = /
        // subtract = -
        return this.cartItemRepository.save(itemSave);
    }

    @Override
    public List<CartItemDto> getAllByCustomerIdAndStatus(Integer customerId, String status) {
        List<CartItem> resultCart = this.cartItemRepository.findByCustomerIdAndStatus(customerId,status);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem itemCart : resultCart){
            CartItemDto dto = new CartItemDto();
            dto.setId(itemCart.getId());
            dto.setPrice(itemCart.getPrice());
            dto.setStatus(itemCart.getStatus());
            dto.setCreateDate(itemCart.getCreatedDate());
            dto.setTotalPrice(itemCart.getTotalPrice());
            dto.setQuantity(itemCart.getQuantity());
            Product product = productService.findById(itemCart.getProductId());
            dto.setProductName(product.getProductName());
            cartItemDtos.add(dto);
        }
        return cartItemDtos;
    }

    @Override
    public void clearCart(Integer customerId) {
        List<CartItem> cartItem = cartItemRepository.findByCustomerId(customerId);
        cartItemRepository.deleteAll(cartItem);
    }

    @Override
    public List<CartItem> findByCustomerId(Integer customerId) {
        return cartItemRepository.findByCustomerId(customerId);
    }

    @Override
    public void removeProductFromCart(Integer productId, Integer customerId) {
        CartItem cartItem = cartItemRepository.findByProductIdAndCustomerId(productId,customerId).orElseThrow(() -> new ObjectNotFoundException("CartItem not found"));
        cartItemRepository.delete(cartItem);
    }



    @Override
    public Optional<CartItem> findByProductIdAndCustomerId(Integer productId, Integer customerId) {
        return cartItemRepository.findByProductIdAndCustomerId(productId,customerId);
    }

    @Override
    public CartItem updateCartItem(Integer cartId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartId)
                .orElseThrow(() -> new ObjectNotFoundException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void checkoutCart(Integer customerId) {
        List<CartItem> cartItem = cartItemRepository.findByCustomerIdAndStatus(customerId,"DRA");
        for (CartItem item : cartItem){
            item.setStatus("COM");
        }
        cartItemRepository.saveAll(cartItem);
    }


}
