package com.app.service;

import com.app.model.Cart;

public interface CartService extends BaseModel<Cart,Integer>{
    Integer findMaxId();

}
