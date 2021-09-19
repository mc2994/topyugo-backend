package com.topyougo.productimport.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topyougo.productimport.constant.Status;
import com.topyougo.productimport.constant.UserType;
import com.topyougo.productimport.dto.UserDTO;
import com.topyougo.productimport.model.User;
import com.topyougo.productimport.model.UserRoles;
import com.topyougo.productimport.modelmapper.UserEntityMapper;
import com.topyougo.productimport.repository.UserRepository;
import com.topyougo.productimport.repository.UserRolesRepository;
import com.topyougo.productimport.security.JwtProvider;
import com.topyougo.productimport.security.JwtResponse;
import com.topyougo.productimport.service.impl.UserPrinciple;
import com.topyougo.productimport.util.JsonUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User authentication and Registration")
@RestController
@RequestMapping("/api")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userService;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Operation(summary = "User login",
		responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", 
				content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = UserDTO.class))}),
	        @ApiResponse(description = "Bad Request", responseCode = "400")})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO user) {
		System.out.println("Username "+user.getUsername());
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

	@Operation(summary = "User Registration",
		responses = {
			@ApiResponse(description = "User successfully registered", responseCode = "200", 
				content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = UserDTO.class))}),
	        @ApiResponse(description = "Username and Email Already Exist", responseCode = "400")})
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO user) {
		System.out.println("............ "+JsonUtil.toJson(UserEntityMapper.mapDTOToModel(user)));
		User userResponse = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		if (userResponse != null) {
			throw new EntityExistsException("Username and Email Already Exist");
		}
		
		
		User result = userService.save(UserEntityMapper.mapDTOToModel(user));

		UserRoles roles = new UserRoles();
		roles.setUserId(result.getId());
		roles.setRoleId(1);

		userRolesRepository.save(roles);
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@Operation(summary = "Update User Information", security = @SecurityRequirement(name = "bearerAuth"),
		responses = {
			@ApiResponse(description = "User successfully updated", responseCode = "200", 
				content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = UserDTO.class))}),
	        @ApiResponse(description = "Username and Email Not Found", responseCode = "404")})
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO user) {
		User userResponse = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		if (userResponse == null) {
			throw new EntityNotFoundException("Username and Email Not Found");
		}
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setStatus(Status.getValueOf(user.getStatus()).getValue());
		userResponse.setUserType(UserType.valueOf(user.getUserType()).getValue());

		userService.save(userResponse);
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@Operation(summary = "Fetch all users with ADMIN user type", security = @SecurityRequirement(name = "bearerAuth"),
		responses = {
			@ApiResponse(description = "Fetch all users by Admin", responseCode = "200", 
				content = @Content(mediaType = "application/json")),
	        @ApiResponse(description = "Username and Email Not Found", responseCode = "404")})
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUsers() {
		List<User> userList = userService.findAll();
		return new ResponseEntity<List<UserDTO>>(UserEntityMapper.mapModelListToDTO(userList), HttpStatus.OK);
	}
}