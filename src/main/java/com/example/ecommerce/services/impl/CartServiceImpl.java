package com.example.ecommerce.services.impl;

import com.example.ecommerce.entities.Cart;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.payload.CartDto;
import com.example.ecommerce.payload.ProductDto;
import com.example.ecommerce.payload.UserDto;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.services.CartService;
import com.example.ecommerce.services.ProductService;
import com.example.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Override
    public CartDto addCartItem(CartDto cartDto,String uId,Integer pId) {

        UserDto userDto = this.userService.getUserById(uId);
        ProductDto productDto = this.productService.getProduct(pId);

        Cart cart = this.modelMapper.map(cartDto, Cart.class);
        cart.setCreatedAt(new Date());
        cart.setUser(this.modelMapper.map(userDto,User.class));
        cart.setProduct(this.modelMapper.map(productDto, Product.class));
        Cart savedCartItem = this.cartRepository.save(cart);
        return this.modelMapper.map(savedCartItem, CartDto.class);
    }

    @Override
    public CartDto updateCartItem(CartDto cartDto, Integer cartId) {
        Cart cart = getCartById(cartId);
        cart.setQuantity(cartDto.getQuantity());
        cart.setModifiedAt(new Date());
        Cart updatedCartItem = this.cartRepository.save(cart);
        return this.modelMapper.map(updatedCartItem, CartDto.class);
    }

    @Override
    public void deleteCartItem(Integer cartId) {
        Cart cart = this.getCartById(cartId);
        this.cartRepository.delete(cart);
    }

    @Override
    public CartDto getCartItemById(Integer cartId) {
        Cart cart = getCartById(cartId);
        return this.modelMapper.map(cart, CartDto.class);
    }

    @Override
    public List<CartDto> getCartItemByUser(String uId) {
        UserDto userDto = this.userService.getUserById(uId);
        List<Cart> cartByUsers = this.cartRepository.findCartByUser(this.modelMapper.map(userDto, User.class));
        return cartByUsers.stream().map(cart -> this.modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CartDto> getAllCartItems() {
        List<Cart> cartList = this.cartRepository.findAll();
        List<CartDto> cartDtos = cartList.stream().map(cart -> this.modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
        return cartDtos;
    }

    private Cart getCartById(Integer cartId) {
        return this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cart id", cartId));
    }
}
