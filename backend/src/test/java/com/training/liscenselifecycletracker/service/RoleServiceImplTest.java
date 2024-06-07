package com.training.liscenselifecycletracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.repositories.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private RoleServiceImpl roleService;

	@Test
	public void testFindRoleByName() {
		// Mocking data
		Role role = new Role(1, ERole.ROLE_USER);
		when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));

		// Calling service method
		Optional<Role> result = roleService.findRoleByName(ERole.ROLE_USER);

		// Assertions
		assertEquals(Optional.of(role), result);
	}

	@Test
	public void testFindRoleById() {
		// Mocking data
		Role role = new Role(1, ERole.ROLE_USER);
		when(roleRepository.findById(1)).thenReturn(Optional.of(role));

		// Calling service method
		Optional<Role> result = roleService.findRoleById(1);

		// Assertions
		assertEquals(Optional.of(role), result);
	}
}
