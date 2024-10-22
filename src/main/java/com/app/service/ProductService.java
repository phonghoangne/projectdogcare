package com.app.service;

import com.app.model.Product;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import org.springframework.data.domain.Page;

public interface ProductService extends BaseModel<Product,Integer> {

    GlobalResponsePagination getAll(ProductRequest request);
}
