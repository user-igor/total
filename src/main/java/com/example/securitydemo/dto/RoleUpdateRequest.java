package com.example.securitydemo.dto;

import java.util.Set;

public record RoleUpdateRequest(Set<String> roles) {
}
