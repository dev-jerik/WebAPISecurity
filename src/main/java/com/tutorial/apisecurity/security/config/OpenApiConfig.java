package com.tutorial.apisecurity.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
      info = @Info(title = "Title", version = "version", 
                     description = "API Documentation for CMS", 
                     contact = @Contact(name = "MyCms", 
                                       email = "mycms.support@gmail.com", 
                                       url = "http://mycms.com/support")))
@SecurityScheme(
      name = "jwt_scheme",
      type = SecuritySchemeType.HTTP,
      in = SecuritySchemeIn.HEADER,
      scheme = "bearer",
      bearerFormat = "jwt"
 )
public class OpenApiConfig {
}