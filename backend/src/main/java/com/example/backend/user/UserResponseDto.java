package com.example.backend.user;

public record UserResponseDto(Long Id, String name, String email, Role role) {}
