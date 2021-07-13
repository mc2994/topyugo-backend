package com.topyougo.productimport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestConfig  implements WebMvcConfigurer {

	   @Override
	    public void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping( "/**" )
	                .allowedOrigins( "*" )
	                .allowedMethods( "GET", "POST", "DELETE", "PUT" )
	                .allowedHeaders( "*" )	                
	                .exposedHeaders( "Authorization","content-disposition" )
	                .maxAge( 3600 );
	    }
}
