package com.topyougo.productimport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "bearer"
	)
public class OpenApiConfig {
	
	 @Bean
     public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
      return new OpenAPI()
           .info(new Info()
           .title("TopYuGo TDI API")
           .version(appVersion)
           .description(appDesciption)
           .termsOfService("http://swagger.io/terms/")
           .license(new License().name("Apache 2.0").url("http://springdoc.org"))
           .contact(new Contact()
        		   .email("tolentino.mckinley@gmail.com")
        		   .name("Mc Kinley L. Tolentino")
        		   .url("https://www.linkedin.com/in/mckinleytolentino/")));
     }
}