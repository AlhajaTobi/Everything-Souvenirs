package com.skillnest.everythingsouvneirs;

import com.skillnest.everythingsouvneirs.data.model.User;
import com.skillnest.everythingsouvneirs.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EverythingSouvenirsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverythingSouvenirsApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UserService userService) {
        return args -> {
            if (userService.findByEmail("admin@everythingsouvenirs.ng").isEmpty()) {
                userService.createUser(new User(
                        "123e4567-e89b-12d3-a456-426614174000",
                        "admin@everythingsouvenirs.ng",
                        "admin123",
                        "ADMIN",
                        "Admin User"
                ));
            }
        };
    }

}
