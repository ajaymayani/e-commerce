package com.example.ecommerce.controllers;

import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.payload.UserDto;
import com.example.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{uId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String uId) {
        UserDto userDto1 = this.userService.updateUser(userDto, uId);
        return ResponseEntity.ok(userDto1);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{uId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String uId) {
        this.userService.deleteUser(uId);
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessages("User deleted successfully...");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String uId) {
        UserDto userById = this.userService.getUserById(uId);
        return ResponseEntity.ok(userById);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
