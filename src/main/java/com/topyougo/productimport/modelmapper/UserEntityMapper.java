package com.topyougo.productimport.modelmapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.topyougo.productimport.dto.UserDTO;
import com.topyougo.productimport.model.User;

public class UserEntityMapper {

	@Autowired
	private static BCryptPasswordEncoder bCryptPasswordEncoder;

	private static ModelMapper modelMapper = new ModelMapper();

	public static User mapDTOToModel(UserDTO dto) {
		User user = modelMapper.map(dto, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		return user;
	}

	public static List<UserDTO> mapModelListToDTO(List<User> usersList) {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		for (User user : usersList) {
			UserDTO userDto = mapModelToDTO(user);
			userList.add(userDto);
		}
		return userList;
	}

	public static UserDTO mapModelToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
}