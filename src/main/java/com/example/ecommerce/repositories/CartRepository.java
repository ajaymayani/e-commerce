package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Cart;
import com.example.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findCartByUser(User user);
}
