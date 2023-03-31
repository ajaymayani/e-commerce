package com.example.ecommerce;

import com.example.ecommerce.entities.Role;
import com.example.ecommerce.helper.AppConstant;
import com.example.ecommerce.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            Role user = new Role();
            user.setRId(AppConstant.ROLE_USER_ID);
            user.setName(AppConstant.ROLE_USER);

            Role admin = new Role();
            admin.setRId(AppConstant.ROLE_ADMIN_ID);
            admin.setName(AppConstant.ROLE_ADMIN);
            List<Role> roleList = List.of(user, admin);
            List<Role> roles = this.roleRepository.saveAll(roleList);
            System.out.println(roles);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
