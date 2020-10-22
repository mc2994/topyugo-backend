package com.topyougo.productimport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.topyougo.productimport.component.UtilityClass;
import com.topyougo.productimport.model.User;
import com.topyougo.productimport.service.ApplicationUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return UserPrinciple.build(applicationUser);
	}
}