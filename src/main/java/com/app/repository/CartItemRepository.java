package com.app.repository;

import com.app.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    @Query(value = "select u from CartItem u where u.customerId = :customerId and u.status = :status") // co 3 cah viet query
    List<CartItem> findByCustomerIdAndStatus(Integer customerId,String status);
}
