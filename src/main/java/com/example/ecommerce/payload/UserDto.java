package com.example.ecommerce.payload;

import com.example.ecommerce.entities.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private String uId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String about;
    List<Role> roles = new ArrayList<>();
}
