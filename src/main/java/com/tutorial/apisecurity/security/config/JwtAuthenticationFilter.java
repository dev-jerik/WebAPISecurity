package com.tutorial.apisecurity.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.apisecurity.security.payload.LoginRequest;
import com.tutorial.apisecurity.security.user.SpringSecurityUser;
import com.tutorial.apisecurity.security.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * This class will be responsible for authenticating the user.
 * We don't need to create a login controller because spring security already have a default Endpoint("/login") for login.
 * 
 * @author Jerik
 * @email jgbeltran.dev@gmail.com
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
   private final AuthenticationManager authenticationManager;

   /**
    * Constructor for dependency injection.
    */
   public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
      this.authenticationManager = authenticationManager;
      setFilterProcessesUrl("/auth/login");
   }

   @Override
   public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

      LoginRequest user = null;
      try {
         user = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
         return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      } catch (BadCredentialsException e) {
         log.warn(e.getMessage());
         throw e;
      } catch (Exception e) {
         log.error(ExceptionUtils.getStackTrace(e));
         throw new RuntimeException(e);
      }

   }

   @Override
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
         throws IOException, ServletException {
      SpringSecurityUser user = (SpringSecurityUser) authResult.getPrincipal();
      response.addHeader("Authorization", "Bearer " + JwtUtils.generateToken(user.getUsername()));
   }

   @Override
   protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
         throws IOException, ServletException {

      String warnMessage = "Username or Password is incorrect.";

      PrintWriter writer = response.getWriter();
      writer.println(warnMessage);

      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      log.warn(warnMessage);
   }
}
