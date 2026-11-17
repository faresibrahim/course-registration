package com.example.backend.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller 
public class UserController {
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService=userService;
  }

  @PostMapping("/create")
  public User createUser(String email, String passwordHash, String name, Role role) {
    return userService.createUser(email, passwordHash, name, role);  
  } 

}
