package com.example.ecommerce.controllers;

import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.payload.ProductDto;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.FileService;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Value("${project.image}")
    private String path;
    @Autowired
    private FileService fileService;

    @PostMapping("/category/{cId}/products")
    public ResponseEntity<ProductDto> createPost(@RequestBody ProductDto productDto, @PathVariable Integer cId) {
        ProductDto product = this.productService.createProduct(productDto, cId);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{pId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer pId) {
        ProductDto productDto1 = this.productService.updateProduct(productDto, pId);
        return ResponseEntity.ok(productDto1);
    }

    @DeleteMapping("/products/{pId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer pId) {
        this.productService.deleteeProduct(pId);
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessages("Product deleted successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{pId}")
    public ResponseEntity<ProductDto> getProductByPId(@PathVariable Integer pId) {
        ProductDto product = this.productService.getProduct(pId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products/category/{cId}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryId(@PathVariable Integer cId) {
        List<ProductDto> products = this.productService.getProductByCategory(cId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = this.productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/products/image/upload/{pId}")
    public ResponseEntity<ProductDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer pId) throws IOException {

        ProductDto product = this.productService.getProduct(pId);
        String imageName = this.fileService.uploadImage(path, image);
        product.setImageUrl(imageName);
        ProductDto productDto = this.productService.updateProduct(product, pId);
        return ResponseEntity.ok(productDto);
    }
}
