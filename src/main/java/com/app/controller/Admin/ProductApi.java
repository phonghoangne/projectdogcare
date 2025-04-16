package com.app.controller.Admin;


import com.app.DTO.ProductDto;
import com.app.Mapper.ProductMapper;
import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import com.app.payload.response.ResponseAPIGlobal;
import com.app.payload.response.ResponseAPIPagination;
import com.app.service.ProductCategoryService;
import com.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/product")
public class ProductApi {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto>  update(@RequestBody ProductDto param,@PathVariable("productId") Integer productId){
       Product  productSave = productService.findById(productId);
        productSave = ProductMapper.updateProduct(param,productSave);
        productSave.setId(productId);
        productSave = productService.save(productSave);
        ProductCategory category = productCategoryService.findById(productSave.getCategoryId());
        param = ProductMapper.toProductDto(productSave,category.getCategoryName());
        return ResponseEntity.ok(param);
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseAPIGlobal> save(@Valid @RequestBody ProductDto param){
        ResponseAPIGlobal response = new ResponseAPIGlobal();
        Product productSave = ProductMapper.toProduct(param);
        productSave = productService.save(productSave);
        ProductCategory category = productCategoryService.findById(productSave.getCategoryId());
        param = ProductMapper.toProductDto(productSave,category.getCategoryName());
        response.setData(param);
        response.setMessage("Create Product success!!!");
        response.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> delete(@PathVariable Integer productId)
    {
        productService.delete(productId);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseAPIPagination> getAll(@ModelAttribute ProductRequest request)
    {
        GlobalResponsePagination listData = productService.getAll(request);
        ResponseAPIPagination response = new ResponseAPIPagination();
        response.setTotalPage(listData.getTotalPage());
        response.setCurrentPage(listData.getCurrentPage());
        response.setTotalItem(listData.getTotalItem());
        response.setData(listData.getData());
        response.setStatus(HttpStatus.OK.value());
        response.setError(null);
        response.setMessage("Get All Product Success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
