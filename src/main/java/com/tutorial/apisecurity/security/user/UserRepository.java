package com.tutorial.apisecurity.security.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {
   private List<User> users;

   private ObjectMapper mapper = new ObjectMapper();

   public UserRepository() {
      try {
         // Use for In-memory authentication.
         File file = ResourceUtils.getFile("classpath:user-credentials.json");
         this.users = mapper.readValue(file, new TypeReference<List<User>>() {
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public Optional<User> findByUsername(String username) {
      return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
   }
}
