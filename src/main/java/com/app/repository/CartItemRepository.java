package com.app.repository;

import com.app.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value = "select u from CartItem u where u.customerId = :customerId and u.status = :status")
    List<CartItem> findByCustomerIdAndStatus(Integer customerId, String status);

    List<CartItem> findByCustomerId(Integer customerId);

    Optional<CartItem> findByProductIdAndCustomerId(Integer productId, Integer customerId);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartId")
    int updateCartItem(Integer cartId, Integer quantity);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.status = 'CHECKED_OUT' WHERE c.customerId = :customerId AND c.status = 'PENDING'")
    int checkoutCart(Integer customerId);
}