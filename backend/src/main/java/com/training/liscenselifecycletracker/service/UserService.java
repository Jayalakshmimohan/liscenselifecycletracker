package com.training.liscenselifecycletracker.service;

import java.util.Optional;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.entities.User;
import com.training.liscenselifecycletracker.exceptions.UserNotFoundException;

public interface UserService {
	
		public User addUserEntity(User user);
	 
		public String updateRole(Long userId, Role role) throws UserNotFoundException;
	 
		public Optional<User> findByUsername(String username);
	 
		public Boolean existsByUsername(String username);
	 
		public Optional<User> findByRole(ERole role);

}
