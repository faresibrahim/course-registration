package com.example.backend.course;

import jakarta.persistence.*;


@Entity 
@Table(name="courses")
public class Course {
  
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private int capacity;

  protected Course() {}

  public Course(String code, String title, int capacity) {
    this.code = code;
    this.title = title;
    this.capacity = capacity;
  }

  //getters

  public Long getId() { return id;}
  public String getCode() { return code;}
  public String getTitle() { return title;}
  public int getCapacity() { return capacity;}


}
