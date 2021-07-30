package com.tutorial.apisecurity.security.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
   private String username;
   private String password;
   private boolean enabled;
}
