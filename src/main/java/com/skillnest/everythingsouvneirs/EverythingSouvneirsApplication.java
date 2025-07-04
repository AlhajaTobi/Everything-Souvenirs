package com.skillnest.everythingsouvneirs;

import com.skillnest.everythingsouvneirs.data.model.User;
import com.skillnest.everythingsouvneirs.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EverythingSouvneirsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverythingSouvneirsApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UserService userService) {
        return args -> {
            if (!userService.findByEmail("admin@everythingsouvenirs.ng").isPresent()) {
                userService.createUser(new User(
                        "admin@everythingsouvenirs.ng",
                        "admin123",
                        "ADMIN",
                        "Admin User"
                ));
            }
        };
    }

}
