package com.tutorial.apisecurity.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tutorial.apisecurity.security.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Class that validates the bearer token from the Authorization Header.
 * 
 * @author Jerik
 * @email jgbeltran.dev@gmail.com
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

   private final UserDetailsService userDetailsService;

   public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
      super(authenticationManager);
      this.userDetailsService = userDetailsService;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
      log.info("Intercepted by AuthTokenFilter.");
      String username = null;

      try {
         String token = parseToken(request);

         if (token != null && JwtUtils.checkToken(token)) {

            username = JwtUtils.getUsernameFromToken(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                  userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      } catch (AuthenticationException e) {
         log.error(e.getMessage());
      } catch (Exception e) {
         logger.error("Cannot set user authentication: {}", e);
      }

      chain.doFilter(request, response);
   }

   private String parseToken(HttpServletRequest request) {
      int jwtStart = 7;
      String headerAuth = request.getHeader("Authorization");

      if (StringUtils.isNoneBlank(headerAuth) && headerAuth.startsWith("Bearer ")) {
         return headerAuth.substring(jwtStart, headerAuth.length());
      }

      return null;
   }
}
