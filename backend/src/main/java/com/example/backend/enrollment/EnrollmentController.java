package com.example.backend.enrollment;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.course.Course;
import com.example.backend.course.CourseResponseDto;
import com.example.backend.course.CourseService;
import com.example.backend.user.UserService;

@RestController 
@RequestMapping("/enrollments")
public class EnrollmentController {
  private final EnrollmentService enrollmentService;
  private final CourseService courseService;
  private final UserService userService;

  public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService, UserService userService) {
    this.enrollmentService=enrollmentService;
    this.courseService = courseService;
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<EnrollmentResponseDto> register(@RequestHeader("X-User-Email") String email, @RequestBody EnrollmentRequestDto request) {

    Enrollment enrollment = enrollmentService.register(email, request.courseId());
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(enrollment));
  
  }


  private EnrollmentResponseDto toResponse(Enrollment enrollment) {
    Course course = enrollment.getCourse();
    return new EnrollmentResponseDto(course.getId(), course.getCode(), course.getTitle(), enrollment.getEnrolledAt());
  }

}
