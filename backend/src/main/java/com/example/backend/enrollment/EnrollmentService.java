package com.example.backend.enrollment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.course.Course;
import com.example.backend.course.CourseService;
import com.example.backend.user.User;
import com.example.backend.user.UserService;

@Service
public class EnrollmentService {
  private final EnrollmentRepository enrollmentRepository;
  private final UserService userService;
  private final CourseService courseService;

  public EnrollmentService(EnrollmentRepository enrollmentRepository, UserService userService, CourseService courseService) {
    this.enrollmentRepository=enrollmentRepository;
    this.userService=userService;
    this.courseService=courseService;
  }

  @Transactional
  public Enrollment register(String email, Long courseId) {
    User user = userService.findUser(email);
    Course course = courseService.getCourseById(courseId);

    if(enrollmentRepository.existsByUserIdAndCourseId(user.getId(), courseId)) {
      throw new IllegalArgumentException("Already registered in this course");
    }
    if(enrollmentRepository.countByCourseId(courseId) >= course.getCapacity()) {
      throw new IllegalArgumentException("Course is full");
    }

    Enrollment enrollment = new Enrollment(user, course);
    return enrollmentRepository.save(enrollment);
  }

  @Transactional
  public void drop(String email, Long courseId) {
    User user = userService.findUser(email);

    if(!enrollmentRepository.existsByUserIdAndCourseId(user.getId(), courseId)) {
      throw new IllegalArgumentException("Not registered in this course");
    }

    enrollmentRepository.deleteByUserIdAndCourseId(user.getId(), courseId);
  }

  public List<Enrollment> getMyEnrollments(String email) {
    User user = userService.findUser(email);
    List<Enrollment> enrollments = enrollmentRepository.findByUserId(user.getId());
    return enrollments;
  }

  public Long getSeatsLeft(Long courseId) {
    Course course = courseService.getCourseById(courseId);
    long seatsLeft = course.getCapacity() - enrollmentRepository.countByCourseId(courseId);
    return seatsLeft;
  }
}