package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.model.Product;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


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
    public Optional<Product> findById(Integer integer) {
        return Optional.empty();
    }


    @Override
    public void delete(Product product) {

    }

    @Override
    public GlobalResponsePagination getAll(ProductRequest request) {
        // page
        //1 page record - tong so page - tong so item
        // java lambda
        //page = 2 // pageSize = 5
        //pageable cua thang jpa tu tinh la 5 phan tu thi no chia dc may trang  vd chia dc 10 trang ma minh truyen vao la page = 2
        // => 5 phan cua page 2
        // specification : tao ra dieu kien where cho cau truy vann
        // pageable tao ra dooi tuong phan trang
        // truyen vao ham findAll cua  jpa => se lay ra cac phan tu thoa man 2 dieu kien tren
        Pageable page = PageRequest.of(request.getPage(), request.getPageSize()); // ho tro phan trang
        Specification<Product>spec = ProductSpecification.getSpect(request);

        Page<Product> result = productRepository.findAll(spec,page);
        GlobalResponsePagination response = new GlobalResponsePagination();

        List<Product> product = new ArrayList<>();

        result.getContent().forEach(item->{
            product.add(item);
        });// java lambda

        /*
        doi tuong page
        => total page
        => total element
        => current page

         */
        //
        response.setData(product);
        response.setTotalPage(result.getTotalPages());
        response.setTotalItem(result.getTotalElements());
        response.setCurrentPage(result.getNumber());
        return response;
    }

    @Override
    public List<Product> searchProductByName(String name) {
        Specification<Product> spec = ProductSpecification.likeName(name);
        return productRepository.findAll(spec);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }




}
