package com.example.backend.enrollment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    long countByCourseId(Long courseId);

    List<Enrollment> findByUserId(Long userId);

    void deleteByUserIdAndCourseId(Long userId, Long courseId);
}
