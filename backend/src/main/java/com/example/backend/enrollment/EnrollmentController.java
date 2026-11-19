package com.example.backend.enrollment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.course.Course;


@RestController 
@RequestMapping("/enrollments")
public class EnrollmentController {
  private final EnrollmentService enrollmentService;

  public EnrollmentController(EnrollmentService enrollmentService) {
    this.enrollmentService=enrollmentService;
  }

  @PostMapping
  public ResponseEntity<EnrollmentResponseDto> register(@RequestHeader("X-User-Email") String email, @RequestBody EnrollmentRequestDto request) {

    Enrollment enrollment = enrollmentService.register(email, request.courseId());
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(enrollment));
  }

  @GetMapping 
  public ResponseEntity<List<EnrollmentResponseDto>> getEnrollments(@RequestHeader("X-User-Email") String email) {
    List<EnrollmentResponseDto> enrollments = enrollmentService.getMyEnrollments(email).stream().map(this::toResponse).toList();
    return ResponseEntity.ok(enrollments);
  }

  @DeleteMapping("/{courseId}") 
  public ResponseEntity<Void> dropEnrollment(@RequestHeader("X-User-Email") String email, @PathVariable Long courseId) {
    enrollmentService.drop(email, courseId);
    return ResponseEntity.noContent().build();

  }

  private EnrollmentResponseDto toResponse(Enrollment enrollment) {
    Course course = enrollment.getCourse();
    return new EnrollmentResponseDto(course.getId(), course.getCode(), course.getTitle(), enrollment.getEnrolledAt());
  }

}
