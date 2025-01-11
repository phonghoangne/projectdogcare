package com.app.controller.Admin;

import com.app.DTO.ProductDto;
import com.app.model.Product;
import com.app.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
public class RestApi {
    private final ProductService productService;
    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct(){
        List<ProductDto> listData = productService.getAllProductDto();
        Map<String,Object> mapRespone = new HashMap<>();
        mapRespone.put("statusCode", HttpStatus.OK.value());
        mapRespone.put("data",listData);
        mapRespone.put("message","Call api success");
        mapRespone.put("error","");
        return ResponseEntity.ok(mapRespone);
    }
    @GetMapping("getProductByName")
    public ResponseEntity<?> getProductByName( @RequestParam("productName") String productName){
        List<Product> listData = productService.searchProductByName(productName);
        Map<String,Object> mapRespone = new HashMap<>();
        mapRespone.put("statusCode",HttpStatus.OK.value());
        mapRespone.put("data",listData);
        mapRespone.put("message","Call api success");
        mapRespone.put("error","");
        return ResponseEntity.ok(mapRespone);
    }
}
