package com.app.service;

import com.app.model.ProductCategory;

import java.util.Optional;

public interface ProductCategoryService extends BaseModel<ProductCategory, Integer> {

    ProductCategory findById(Integer id);
}
