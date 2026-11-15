package com.example.backend.enrollment;

import org.springframework.stereotype.Service;

@Service 
public class EnrollmentService {
  private final EnrollmentRepository enrollmentRepository;
  
  //dependency injection
  public EnrollmentService(EnrollmentRepository enrollmentRepository) {
    this.enrollmentRepository=enrollmentRepository;
  }


}
