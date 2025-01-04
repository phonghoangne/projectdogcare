package com.app.repository;

import com.app.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query("select max(o.id) from Cart o")
    Integer findMaxId();
}
