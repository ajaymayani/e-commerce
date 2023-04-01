package com.example.ecommerce.services.impl;

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.payload.CategoryDto;
import com.example.ecommerce.payload.ProductDto;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto,Integer cId) {
        Product product = this.modelMapper.map(productDto, Product.class);
        CategoryDto categoryDto = this.categoryService.getCategoryById(cId);
//        product.setPId(UUID.randomUUID().toString());
        product.setCreatedAt(new Date());
        product.setCategory(this.modelMapper.map(categoryDto, Category.class));
        Product saved = this.productRepository.save(product);
        return this.modelMapper.map(saved, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer pId) {
        ProductDto productDto1 = getProduct(pId);
        Product product = this.modelMapper.map(productDto1, Product.class);
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductQuantity(productDto.getProductQuantity());
        if(productDto.getImageUrl() != null)
            product.setImageUrl(Optional.ofNullable(productDto.getImageUrl()).orElse(null));
        else
            product.setModifiedAt(new Date());
        Product updated = this.productRepository.save(product);
        return this.modelMapper.map(updated, ProductDto.class);
    }

    @Override
    public void deleteeProduct(Integer pId) {
        Product productById = getProductById(pId);
        this.productRepository.delete(productById);
    }

    @Override
    public ProductDto getProduct(Integer pId) {
        Product product = getProductById(pId);
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductByCategory(Integer cId) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(cId);
        List<Product> products = this.productRepository.findProductByCategory(this.modelMapper.map(categoryDto, Category.class));
        List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }

    private Product getProductById(Integer pId) {
        return this.productRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", "product id", pId));
    }
}
