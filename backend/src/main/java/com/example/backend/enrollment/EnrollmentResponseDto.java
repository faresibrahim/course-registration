package com.example.backend.enrollment;

import java.time.LocalDateTime;

public record EnrollmentResponseDto (Long courseId, String courseCode, String title, LocalDateTime enrolledAt){}
