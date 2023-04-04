package com.example.ecommerce.controllers;

import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.payload.CartDto;
import com.example.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/user/{uId}/product/{pId}")
    public ResponseEntity<CartDto> addCartItem(@RequestBody CartDto cartDto,@PathVariable String uId,@PathVariable Integer pId) {
        CartDto cartDto1 = this.cartService.addCartItem(cartDto,uId,pId);
        return new ResponseEntity<>(cartDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> updateCartItem(@RequestBody CartDto cartDto, @PathVariable Integer cartId) {
        CartDto cartDto1 = this.cartService.updateCartItem(cartDto, cartId);
        return ResponseEntity.ok(cartDto1);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Integer cartId) {
        this.cartService.deleteCartItem(cartId);
        ApiResponse response = new ApiResponse();
        response.setMessages("Cart item deleted successfully...");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartItemByCartId(@PathVariable Integer cartId) {
        CartDto cartItemByCartId = this.cartService.getCartItemById(cartId);
        return ResponseEntity.ok(cartItemByCartId);
    }

    @GetMapping("/user/{uId}")
    public ResponseEntity<List<CartDto>> getCartItemByUser(@PathVariable String uId) {
        List<CartDto> cartItemByUser = this.cartService.getCartItemByUser(uId);
        return ResponseEntity.ok(cartItemByUser);
    }


    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCartItems() {
        List<CartDto> cartItems = this.cartService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }
}
