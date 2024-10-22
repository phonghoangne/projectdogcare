package com.app.service.Impl;

import com.app.model.Product;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import com.app.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public GlobalResponsePagination getAll(ProductRequest request) {
        // page
        //1 page record - tong so page - tong so item
        // java lambda
        Pageable page = PageRequest.of(request.getPage(), request.getPageSize()); // ho tro phan trang
        Specification<Product>spec = ProductSpecification.getSpect(request);
        Page<Product> result = productRepository.findAll(spec,page);
        GlobalResponsePagination response = new GlobalResponsePagination();
        List<Product> product = new ArrayList<>();

        result.getContent().forEach(item->{
            product.add(item);
        });// java lambda
        response.setData(product);
        response.setTotalPage(result.getTotalPages());
        response.setTotalItem(result.getTotalElements());
        response.setCurrentPage(result.getNumber());
        return response;
    }
}
