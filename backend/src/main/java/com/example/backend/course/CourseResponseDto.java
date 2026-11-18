package com.example.backend.course;

public record CourseResponseDto(Long id, String code, String title, int capacity, long seatsLeft) {}
