package com.app.service;

import com.app.DTO.ProductDto;
import com.app.model.Product;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends BaseModel<Product,Integer> {

    GlobalResponsePagination getAll(ProductRequest request);

    List<Product> searchProductByName(String name);

    List<ProductDto> getAllProductDto();
}
