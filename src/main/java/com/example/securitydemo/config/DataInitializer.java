package com.example.securitydemo.config;

import com.example.securitydemo.model.Role;
import com.example.securitydemo.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(AppUserService appUserService) {
        return args -> {
            appUserService.createUser("admin", "admin123", Set.of(Role.ROLE_ADMIN, Role.ROLE_USER));
            appUserService.createUser("user", "user123", Set.of(Role.ROLE_USER));
        };
    }
}
