package com.tutorial.apisecurity.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.apisecurity.security.payload.LoginRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This class was created to be able to document the api endpoint of spring security. 
 * source: https://stackoverflow.com/questions/34386337/documenting-springs-login-logout-api-in-swagger
 * 
 * @author Jerik
 * @email jgbeltran.dev@gmail.com
 */
@RestController
@Tag(name = "Login API")
public class LoginController {

   @PostMapping("/auth/login")
   @Operation(summary = "Login", description = "No authentication or authorization required.")
   public void fakeLogin(@RequestBody LoginRequest req) {
      throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
   }

//   @ApiOperation("Logout.")
//   @PostMapping("/logout")
//   public void fakeLogout() {
//      throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
//   }
}
