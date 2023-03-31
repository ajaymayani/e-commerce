package com.example.ecommerce.services;

import com.example.ecommerce.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create Category
    CategoryDto createCategory(CategoryDto categoryDto);

    //update Category
    CategoryDto updateCategory(CategoryDto categoryDto, String cId);

    //delete Category
    void deleteCategory(String cId);

    //fetch single Category
    CategoryDto getCategoryById(String cId);

    //fetch all Category
    List<CategoryDto> getAllCategorys();
}
