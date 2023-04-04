package com.example.ecommerce.services;

import com.example.ecommerce.payload.CartDto;

import java.util.List;

public interface CartService {

    CartDto addCartItem(CartDto cartDto,String uId,Integer pId);

    CartDto updateCartItem(CartDto cartDto,Integer cartId);

    void deleteCartItem(Integer cartId);

    CartDto getCartItemById(Integer cartId);

    List<CartDto> getCartItemByUser(String uId);

    List<CartDto> getAllCartItems();
}
