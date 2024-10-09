package com.app.service.Impl;

import com.app.model.ProductCategory;
import com.app.repository.ProductCategoryRepository;
import com.app.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceimpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory update(ProductCategory id) {
        return productCategoryRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
ProductCategory productCategory = productCategoryRepository.findById(integer).
        orElseThrow(()-> new RuntimeException("Productcategory is not found"));
productCategoryRepository.delete(productCategory);
    }

    @Override
    public ProductCategory findById(Integer integer) {
        return productCategoryRepository.findById(integer).get();
    }
}
