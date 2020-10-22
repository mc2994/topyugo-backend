package com.topyougo.productimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;

@EnableScheduling
@SpringBootApplication
@EnableAsync
public class ProductImportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductImportApplication.class, args);
	}
	
	   @Bean(name = "threadPoolTaskExecutor")
	   public TaskExecutor getAsyncExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(3);
	        executor.setMaxPoolSize(3);
	        executor.setQueueCapacity(100);
	        executor.setThreadNamePrefix("AsynchThread-");
	        executor.initialize();
	        return executor;
	    }

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public HandlerExceptionResolver sentryExceptionResolver() {
		return new io.sentry.spring.SentryExceptionResolver();
	}

	@Bean
	public ServletContextInitializer sentryServletContextInitializer() {
	    return new io.sentry.spring.SentryServletContextInitializer();
	}
}
