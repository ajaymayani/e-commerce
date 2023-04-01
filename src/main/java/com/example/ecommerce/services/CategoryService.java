package com.example.ecommerce.services;

import com.example.ecommerce.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create Category
    CategoryDto createCategory(CategoryDto categoryDto);

    //update Category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer cId);

    //delete Category
    void deleteCategory(Integer cId);

    //fetch single Category
    CategoryDto getCategoryById(Integer cId);

    //fetch all Category
    List<CategoryDto> getAllCategorys();
}
