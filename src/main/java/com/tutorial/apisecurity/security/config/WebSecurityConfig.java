package com.tutorial.apisecurity.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   private final PasswordEncoder passwordEncoder;
   private final UserDetailsService userDetailsService;
   private final BooksWsAuthenticationEntryPoint authenticationEntryPoint;

   public WebSecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService,
         BooksWsAuthenticationEntryPoint authenticationEntryPoint) {
      this.passwordEncoder = passwordEncoder;
      this.userDetailsService = userDetailsService;
      this.authenticationEntryPoint = authenticationEntryPoint;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/books/{id}").hasAuthority("book:view")
            .antMatchers("/books").hasAuthority("book:add")
            .anyRequest().authenticated()
            .and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint);
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
   }
}
