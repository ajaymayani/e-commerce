package com.example.ecommerce.services.impl;

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.payload.CategoryDto;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
//        category.setCId(UUID.randomUUID().toString());
        Category saved = this.categoryRepository.save(category);
        return this.modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer cId) {
        Category category = getCategory(cId);
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updated = this.categoryRepository.save(category);
        return this.modelMapper.map(updated, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer cId) {
        Category category = getCategory(cId);
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer cId) {
        return this.modelMapper.map(getCategory(cId), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategorys() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    private Category getCategory(Integer cId) {
        return this.categoryRepository.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", cId));
    }
}
