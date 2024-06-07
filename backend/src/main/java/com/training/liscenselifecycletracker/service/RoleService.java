package com.training.liscenselifecycletracker.service;

import java.util.Optional;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.Role;
 
 
public interface RoleService {
	
	public Optional<Role> findRoleByName(ERole role);
	public Optional<Role> findRoleById(Integer id);
 
}
