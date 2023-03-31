package com.example.ecommerce.controllers;

import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.payload.CategoryDto;
import com.example.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{cId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String cId) {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, cId);
        return ResponseEntity.ok(categoryDto1);
    }

    @DeleteMapping("/{cId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String cId) {
        this.categoryService.deleteCategory(cId);
        ApiResponse response = new ApiResponse();
        response.setMessages("category deleted successfully...");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cId}")
    public ResponseEntity<CategoryDto> getCategoryByCId(@PathVariable String cId) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(cId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategoryByCId() {
        List<CategoryDto> allCategorys = this.categoryService.getAllCategorys();
        return ResponseEntity.ok(allCategorys);
    }
}
