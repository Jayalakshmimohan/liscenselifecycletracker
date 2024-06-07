package com.training.liscenselifecycletracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.repositories.RoleRepository;
 
 
@Service
public class RoleServiceImpl implements RoleService {
 
	@Autowired
	RoleRepository repo;
	@Override
	public Optional<Role> findRoleByName(ERole role) {
		Optional<Role> r= repo.findByName(role);
		return r;
	}
	@Override
	public Optional<Role> findRoleById(Integer id) {
		Optional<Role> role= repo.findById(id);
		return role;
	}

 
}
