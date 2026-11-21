package com.example.backend.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.common.ConflictException;
import com.example.backend.common.NotFoundException;

@Service 
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository=userRepository;
  }

  public User createUser(String email, String passwordHash, String name, Role role) {
    if(userRepository.existsByEmail(email)) {
      throw new ConflictException("Email is Already registered");
    }

    
    User user = new User(email, passwordHash, name, role);
    return userRepository.save(user);
    
  } 

  public User findUser(String email) {
    
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    
    return user;
  }

  public void removeUser(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new NotFoundException("User not found"));
      
    userRepository.delete(user);

  }

  public List<User> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users;   
  }
}
