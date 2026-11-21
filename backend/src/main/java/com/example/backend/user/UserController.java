package com.example.backend.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService=userService;
  }

  @PostMapping
  public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request) {
    User user = userService.createUser(request.email(), request.passwordHash(), request.name(), request.role());  
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(user));
  } 

  @DeleteMapping
  public ResponseEntity<Void> deleteUser(String email) {
    userService.removeUser(email);
    return ResponseEntity.noContent().build();
  } 

  @GetMapping 
  public ResponseEntity<List<UserResponseDto>> getUsers() {

    List<UserResponseDto> users = userService.getAllUsers().stream().map(this::toResponse).toList();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/search")
  public ResponseEntity<UserResponseDto> findUser(String email) {
    User user = userService.findUser(email);
    return ResponseEntity.ok(toResponse(user));
  }

  private UserResponseDto toResponse(User user) {
    return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
  }

}
