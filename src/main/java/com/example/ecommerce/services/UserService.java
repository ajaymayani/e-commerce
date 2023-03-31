package com.example.ecommerce.services;

import com.example.ecommerce.payload.UserDto;

import java.util.List;

public interface UserService {

    //create user
    UserDto createUser(UserDto userDto);

    //update user
    UserDto updateUser(UserDto userDto,String uId);

    //delete user
    void deleteUser(String uId);

    //fetch single user
    UserDto getUserById(String uId);

    //fetch all user
    List<UserDto> getAllUsers();
}
