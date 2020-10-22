package com.topyougo.productimport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.component.UtilityClass;
import com.topyougo.productimport.dto.Status;
import com.topyougo.productimport.dto.UserDTO;
import com.topyougo.productimport.dto.UserType;
import com.topyougo.productimport.model.User;
import com.topyougo.productimport.model.UserRoles;
import com.topyougo.productimport.repository.UserRepository;
import com.topyougo.productimport.repository.UserRolesRepository;
import com.topyougo.productimport.security.JwtProvider;
import com.topyougo.productimport.security.JwtResponse;
import com.topyougo.productimport.service.impl.UserPrinciple;

@RestController
@RequestMapping("/api")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userService;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO user) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);

		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
		
		List<String> roles = userDetails
								.getAuthorities()
								.stream().map(item -> item.getAuthority())
								.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
					userDetails.getId(), 
					userDetails.getUsername(),
					userDetails.getFirstName(), 
					userDetails.getLastName(), 
					userDetails.getEmail(), 
					roles));
	}
	
//	@GetMapping("/logout")
//	public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
//		System.out.println(">>>>>>> "+token);
//		String aa = "";
//		if (token != null && token.startsWith("Bearer ")) {
//			 aa = token.replace("Bearer ", "");
//		}
//		
//		System.out.println(">>>>>>> "+aa);
//		
//		String name = jwtProvider.invalidateToken(token);
//		return new ResponseEntity<String>(name, HttpStatus.OK);
//	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO user) {
		User userResponse = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		if (userResponse != null) {
			return new ResponseEntity<String>("Username and Email Already Exist", HttpStatus.BAD_REQUEST);
		}
		User result = userService.save(convertDtoToModel(user));

		UserRoles roles = new UserRoles();
		roles.setUserId(result.getId());
		roles.setRoleId(1);

		userRolesRepository.save(roles);
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
		User userResponse = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setStatus(Status.getValueOf(user.getStatus()).getValue());
		userResponse.setUserType(UserType.valueOf(user.getUserType()).getValue());

		userService.save(userResponse);
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUsers() {
		List<User> userList = userService.findAll();
		return new ResponseEntity<List<UserDTO>>(convertUserListToModel(userList), HttpStatus.OK);
	}

	public User convertDtoToModel(UserDTO dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		user.setUsername(dto.getUsername());
		user.setUserType(UserType.valueOf(dto.getUserType()).toString());
		user.setStatus(Status.getValueOf(dto.getStatus()).toString());
		return user;
	}

	private List<UserDTO> convertUserListToModel(List<User> usersList) {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		for (User user : usersList) {
			UserDTO userDto = new UserDTO();
			userDto.setEmail(user.getEmail());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setPassword(user.getPassword());
			userDto.setUsername(user.getUsername());
			userDto.setUserType(UserType.valueOf(user.getUserType()).toString());
			userDto.setStatus(Status.getValueOf(user.getStatus()).getValue());
			userList.add(userDto);
		}
		return userList;
	}
}
