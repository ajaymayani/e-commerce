package com.example.ecommerce.payload;

import lombok.Data;

@Data
public class UserDto {

    private String uId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String about;

}
