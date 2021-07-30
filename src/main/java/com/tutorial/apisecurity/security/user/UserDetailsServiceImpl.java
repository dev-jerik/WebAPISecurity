package com.tutorial.apisecurity.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   private final UserService userService;

   public UserDetailsServiceImpl(UserService userService) {
      this.userService = userService;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userService.findUserByUsername(username);

      if (user != null) {
         return new SpringSecurityUser(user);
      }
      throw new UsernameNotFoundException("User with username, " + username + ", does not exist.");
   }

}
