package com.app.controller.admin;

import com.app.DTO.ProductDto;
import com.app.model.Product;
import com.app.payload.request.ProductRequest;
import com.app.payload.response.GlobalResponsePagination;
import com.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class RestApi {

    private final ProductService productService;

    //@PathVariable @RequestParam @ModelAttribute
    @GetMapping("/getAllProduct") // get post
    public ResponseEntity<?> getAllProduct(){
        List<ProductDto> listData = productService.getAllProductDto();
        Map<String , Object> mapResponse = new HashMap<>();
        mapResponse.put("statusCode", HttpStatus.OK.value());
        mapResponse.put("data",listData);
        mapResponse.put("message","Call API sucess");
        mapResponse.put("error","");
        return ResponseEntity.ok(mapResponse);
    }

    @GetMapping("/getProductByName") // get post
    public ResponseEntity<?> getProductByName( @RequestParam("productName") String productName){
        List<Product> listData = productService.searchProductByName(productName);
        Map<String , Object> mapResponse = new HashMap<>();
        mapResponse.put("statusCode", HttpStatus.OK.value());
        mapResponse.put("data",listData);
        mapResponse.put("message","Call API sucess");
        mapResponse.put("error","");
        return ResponseEntity.ok(mapResponse);
    }
    @GetMapping("/getPaginationProduct") // get post
    public ResponseEntity<?> getPaginationProduct( @ModelAttribute  ProductRequest productRequest){
        GlobalResponsePagination listData = productService.getAll(productRequest);
        Map<String , Object> mapResponse = new HashMap<>();
        mapResponse.put("statusCode", HttpStatus.OK.value());
        mapResponse.put("data",listData.getData());
        mapResponse.put("totalItem",listData.getTotalItem());
        mapResponse.put("totalPage",listData.getTotalPage());
        mapResponse.put("currentPage",listData.getCurrentPage());
        mapResponse.put("message","Call API sucess");
        mapResponse.put("error","");
        return ResponseEntity.ok(mapResponse);
    }
}
