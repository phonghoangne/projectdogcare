package com.app.Mapper;

import com.app.DTO.ProductDto;
import com.app.model.Product;

public class ProductMapper {
    public static ProductDto toProductDto(Product product,String nameCategory){
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getAnimalType(),
                product.getCategoryId(),
                product.getImage(),
               nameCategory
        );
    }
    public static Product toProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getAnimalType(),
               productDto.getCategoryId(),
                productDto.getImage()
        );
    }
    public static Product updateProduct(ProductDto productDto,Product product){
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAnimalType(productDto.getAnimalType());
        product.setCategoryId(productDto.getCategoryId());
        product.setImage(productDto.getImage());
        return product;
    }
}
