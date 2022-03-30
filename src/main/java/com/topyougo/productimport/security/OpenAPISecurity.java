package com.topyougo.productimport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.topyougo.productimport.constant.SecurityConstants;

//@Order(101)
@Configuration("MySecurityConfig")
public class OpenAPISecurity 
//extends WebSecurityConfigurerAdapter 

{
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers(SecurityConstants.AUTH_WHITELIST)
//			.authenticated()
//			.and()
//			.formLogin()
//			.and()
//			.httpBasic();
//	}
//
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.passwordEncoder(bCryptPasswordEncoder)
//			.withUser("user")
//			.password(bCryptPasswordEncoder
//					.encode("password"))
//			.roles("USER"); 
//	}
}
