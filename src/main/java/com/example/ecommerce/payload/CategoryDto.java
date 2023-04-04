package com.example.ecommerce.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private String cId;
    private String categoryName;
    private String categoryDescription;
//    private List<ProductDto> products;
}
