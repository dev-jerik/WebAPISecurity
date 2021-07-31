package com.tutorial.apisecurity.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * This class will be called if there is error upon authentication using basic authentication. For example, invalid credentials.
 * 
 * @author Jerik
 * @email jgbeltran.dev@gmail.com
 */
@Component
@Slf4j
public class BooksWsAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

   @Override
   public void afterPropertiesSet() {
      setRealmName("basicRealm"); // any name you want.
      super.afterPropertiesSet();
   }

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
      response.addHeader("WWW-Authenticate", "Basic realm=" + this.getRealmName());
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      String errorMsg = "Basic Authentication required. Please supply appropriate credentials.";
      PrintWriter writer = response.getWriter();
      writer.println(errorMsg);
      log.warn("Bad Credentials: " + errorMsg);
   }

}
