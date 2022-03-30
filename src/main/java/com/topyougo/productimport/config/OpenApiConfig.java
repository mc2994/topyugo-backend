package com.topyougo.productimport.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
     @Bean
     public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, 
    		 		  @Value("${application-version}") String appVersion) {
      return new OpenAPI()
    	   .components(new Components()
    		   .addSecuritySchemes("bearerAuth",
	            new SecurityScheme()
	            	.type(SecurityScheme.Type.HTTP)
	            	.scheme("bearer")
	            	.bearerFormat("JWT")
	            		.in(SecurityScheme.In.HEADER)
	            		.name("Authorization")))
    	   .addSecurityItem(new SecurityRequirement()
    			   .addList("bearerAuth", 
    					   Arrays.asList("read", "write")))
    	   .addServersItem(new Server().url("http://localhost:5000").description("Local Environment URL"))
    	   .addServersItem(new Server().url("http://www.mytdi.vip/test").description("Test Environment URL"))
    	   .addServersItem(new Server().url("http://www.mytdi.vip/production").description("Production Environment URL"))    	
           .info(new Info()
               .title("TopYuGo TDI API")
               .version(appVersion)
               .description(appDesciption)
               .termsOfService("http://swagger.io/terms/")
               .license(new License()
    		   .name("Apache 2.0")
    		   .url("http://springdoc.org"))
                   .contact(new Contact()
            		   .email("tolentino.mckinley@gmail.com")
            		   .name("Mc Kinley L. Tolentino")
            		   .url("https://www.linkedin.com/in/mckinleytolentino/")));
     }
}