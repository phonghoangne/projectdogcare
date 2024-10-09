package com.app.service.Impl;

import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product integer) {
        return productRepository.save(integer);
    }

    @Override
    public void delete(Integer integer) {
        Product product = productRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public Product findById(Integer integer) {
        return productRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
