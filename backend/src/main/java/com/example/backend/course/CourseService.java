package com.example.backend.course;

import org.springframework.stereotype.Service;

@Service 
public class CourseService {
  private final CourseRepository courseRepository;
  
  //dependency injection
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository=courseRepository;
  }

  

}
