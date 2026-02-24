package com.example.securitydemo.controller;

import com.example.securitydemo.dto.RoleUpdateRequest;
import com.example.securitydemo.model.Role;
import com.example.securitydemo.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final AppUserService appUserService;

    public AdminController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        model.addAttribute("users", appUserService.findAllUsers());
        return "admin-users";
    }

    @PatchMapping("/admin/users/{username}/roles")
    public ResponseEntity<Void> updateRoles(@PathVariable String username, @RequestBody RoleUpdateRequest request) {
        Set<Role> roles = toRoles(request.roles());
        appUserService.updateUserRoles(username, roles);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/admin/users/{username}/roles")
    public String updateRolesFromUi(@PathVariable String username,
                                    @RequestParam(name = "roles", required = false) Set<String> roles,
                                    Model model) {
        Set<Role> mappedRoles = toRoles(roles == null ? Set.of() : roles);
        if (mappedRoles.isEmpty()) {
            mappedRoles = Set.of(Role.ROLE_USER);
        }

        appUserService.updateUserRoles(username, mappedRoles);
        model.addAttribute("users", appUserService.findAllUsers());
        model.addAttribute("message", "Роли пользователя " + username + " обновлены");
        return "admin-users";
    }

    private Set<Role> toRoles(Set<String> roles) {
        return roles.stream()
                .map(String::trim)
                .map(String::toUpperCase)
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
