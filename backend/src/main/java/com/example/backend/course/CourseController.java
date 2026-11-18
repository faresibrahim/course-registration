package com.example.backend.course;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.enrollment.EnrollmentService;


@RestController
@RequestMapping("/courses")
public class CourseController {
  
  private final CourseService courseService;
  private final EnrollmentService enrollmentService;

  public CourseController(CourseService courseService, EnrollmentService enrollmentService) {
    this.courseService = courseService;
    this.enrollmentService=enrollmentService;
  }

  @PostMapping
  public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto request) {
  
    Course course = courseService.createCourse(request.code(), request.title(), request.capacity());
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(course));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseResponseDto> findCourse(@PathVariable Long id) {
    Course course = courseService.getCourseById(id);
    return ResponseEntity.ok(toResponse(course));
  }   


  @GetMapping
  public ResponseEntity<List<CourseResponseDto>> getAll() {
    List<CourseResponseDto> courses = courseService.getAllCourses().stream().map(this::toResponse).toList();
    return ResponseEntity.ok(courses);
  }


  private CourseResponseDto toResponse(Course course) {
    long id = course.getId();
    long seatsLeft = enrollmentService.getSeatsLeft(id);
    return new CourseResponseDto(course.getId(), course.getCode(), course.getTitle(), course.getCapacity(), seatsLeft);
  }

}
