package com.example.backend.user;

public record UserRequestDto(String email, String passwordHash, String name, Role role) {}
