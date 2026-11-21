package com.example.backend.course;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.common.ConflictException;
import com.example.backend.common.NotFoundException;

@Service
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository=courseRepository;
  }

  public Course createCourse(String code, String title, int capacity) {
    if(courseRepository.existsByCode(code)) {
      throw new ConflictException("Course code is already registered");
    }
    if(capacity < 1) {
      throw new IllegalArgumentException("Capacity must be at least 1");
    }

    Course course = new Course(code, title, capacity);
    return courseRepository.save(course);
  }

  public Course getCourseById(Long id) {
    Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

    return course;
  }

  public List<Course> getAllCourses() {
    List<Course> courses = courseRepository.findAll();
    return courses;
  }
}