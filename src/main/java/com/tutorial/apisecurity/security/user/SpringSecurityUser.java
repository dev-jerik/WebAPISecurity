package com.tutorial.apisecurity.security.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringSecurityUser implements UserDetails {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private final User user;

   public SpringSecurityUser(User user) {
      this.user = user;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getPassword() {
      return user.getPassword();
   }

   @Override
   public String getUsername() {
      return user.getUsername();
   }

   @Override
   public boolean isAccountNonExpired() {
      return user.isEnabled();
   }

   @Override
   public boolean isAccountNonLocked() {
      return user.isEnabled();
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return user.isEnabled();
   }

   @Override
   public boolean isEnabled() {
      return user.isEnabled();
   }

}
