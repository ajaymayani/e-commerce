package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Role;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.helper.AppConstant;
import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.payload.Credentials;
import com.example.ecommerce.payload.JwtResponse;
import com.example.ecommerce.payload.UserDto;
import com.example.ecommerce.repositories.RoleRepository;
import com.example.ecommerce.repositories.UserRepository;
import com.example.ecommerce.security.JwtUtils;
import com.example.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) {
        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = this.jwtUtils.generateJwtToken(authenticate);

        User user = (User) authenticate.getPrincipal();

        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(token, user.getUId(), user.getUsername(), user.getEmail(), roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        if (this.userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email is already in use!"));
        }

        List<Role> strRole = userDto.getRoles();
        List<Role> roles = new ArrayList<>();

        if (strRole.size() == 0) {
            Role userRole = this.roleRepository.findById(AppConstant.ROLE_USER_ID).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRole.forEach(role -> {
                switch (role.getName()) {
                    case "admin":
                        Role adminRole = this.roleRepository.findById(AppConstant.ROLE_ADMIN_ID).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "user":
                        Role roleUser = this.roleRepository.findById(AppConstant.ROLE_USER_ID).orElseThrow(() -> new RuntimeException("Error:  Role is not found."));
                        roles.add(roleUser);
                        break;
                    case "consumer":
//                        Role roleUser1 = this.roleRepository.findById(AppConstant.ROLE_USER_ID).orElseThrow(() -> new RuntimeException("Error:  Role is not found."));
//                        roles.add(roleUser1);
                        break;
                }
            });
        }
        userDto.setRoles(roles);
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
