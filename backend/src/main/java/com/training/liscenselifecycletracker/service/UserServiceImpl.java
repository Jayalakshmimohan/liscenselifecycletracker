package com.training.liscenselifecycletracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.entities.User;
import com.training.liscenselifecycletracker.exceptions.UserNotFoundException;
import com.training.liscenselifecycletracker.repositories.UserRepository;
 
 
@Service
public class UserServiceImpl implements UserService {
 
	@Autowired
	UserRepository repo;
	
	
	
	@Override
	public String updateRole(Long userId, Role role) throws UserNotFoundException {
		
		Optional<User> user= repo.findById(userId);
		if(user.isPresent())
		{
		  user.get().setRole(role);
		  repo.save(user.get());
		  return "Role Updated Successfully!!!";
		  
		}
		else {
			throw new UserNotFoundException("User with "+userId+" not found");
		}
	}
 
	@Override
	public User addUserEntity(User user) {
		User userEntity=repo.save(user);
		return userEntity;
	}
 
	@Override
	public Optional<User> findByUsername(String username) {
		Optional<User>  user =repo.findByUsername(username);
		return user;
	}
 
	@Override
	public Boolean existsByUsername(String username) {
		Boolean b=repo.existsByUsername(username);
		return b;
	}
 
	@Override
	public Optional<User> findByRole(ERole role) {
		 Optional<User>  user =repo.findByRole(role);
		return user;
	}
 
	
	
	
 
}
 
