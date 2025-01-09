package com.app.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    String name;
    Integer categoryId;
    Integer page = 0;
    Integer pageSize = 5;
}
