package com.training.liscenselifecycletracker.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);
	 
	public Boolean existsByUsername(String username);
 
	public Boolean existsByEmail(String email);
	
	public Optional<User> findByRole(ERole role);
}

