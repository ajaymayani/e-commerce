package com.example.ecommerce.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDto {
    private String pId;
    private String productName;
    private double productPrice;
    private Integer productQuantity;
    private String productDescription;
    private String imageUrl;
    private Date createdAt;
    private Date modifiedAt;
}
