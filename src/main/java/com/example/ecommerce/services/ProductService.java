package com.example.ecommerce.services;

import com.example.ecommerce.payload.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto,Integer cId);
    ProductDto updateProduct(ProductDto productDto,Integer pId);
    void deleteeProduct(Integer pId);
    ProductDto getProduct(Integer pId);
    List<ProductDto> getProductByCategory(Integer cId);
    List<ProductDto> getAllProducts();
}
