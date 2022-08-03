package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entity.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtotoUser(userDto);
		
		User savedUser = this.userRepo.save(user);
		
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.usertoDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user-> this.usertoDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	
	private User dtotoUser(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto usertoDto(User user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
