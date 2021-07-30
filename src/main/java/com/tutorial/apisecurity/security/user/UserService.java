package com.tutorial.apisecurity.security.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
   private final UserRepository userRepository;

   // Dependency Injection
   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }
   
   public User findUserByUsername(String username) {
      Optional<User> optUser = userRepository.findByUsername(username);

      if (optUser.isPresent()) {
         return optUser.get();
      }
      return null;
   }
}
