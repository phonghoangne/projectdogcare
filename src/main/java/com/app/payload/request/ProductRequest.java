package com.app.payload.request;

import lombok.Data;

@Data
public class ProductRequest {
    String name;
    Integer categoryId;
    Integer page = 0;
    Integer pageSize = 5;
}
