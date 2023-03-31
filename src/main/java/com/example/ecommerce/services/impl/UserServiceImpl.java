package com.example.ecommerce.services.impl;

import com.example.ecommerce.entities.User;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.payload.UserDto;
import com.example.ecommerce.repositories.UserRepository;
import com.example.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);
        user.setUId(UUID.randomUUID().toString());
        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String uId) {
        User user = getUser(uId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepository.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String uId) {
        User user = getUser(uId);
        this.userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(String uId) {
        return this.modelMapper.map(getUser(uId), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    private User getUser(String uId) {
        return this.userRepository.findById(uId).orElseThrow(() -> new ResourceNotFoundException("User", "user id ", uId));
    }
}
