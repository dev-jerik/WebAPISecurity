package com.tutorial.apisecurity.security.payload;

import lombok.Data;

@Data
public class LoginRequest {
//   @NotBlank(message = "Username is required.")
   private String username;
//   @NotBlank(message = "Password is required.")
   private String password;
}