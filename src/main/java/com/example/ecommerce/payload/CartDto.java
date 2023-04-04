package com.example.ecommerce.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartDto {

    private Integer cartId;
    private Integer quantity;
    private Date createdAt;
    private Date modifiedAt;
}
