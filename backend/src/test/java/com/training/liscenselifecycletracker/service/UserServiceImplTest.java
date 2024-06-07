package com.training.liscenselifecycletracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.liscenselifecycletracker.entities.ERole;
import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.entities.User;
import com.training.liscenselifecycletracker.exceptions.UserNotFoundException;
import com.training.liscenselifecycletracker.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;
	private Role role;

	@BeforeEach
	public void setup() {
		role = new Role(1, ERole.ROLE_USER);
		user = new User(1L, "username", "email@example.com", role, "password");
	}

	@Test
	public void testUpdateRole_UserExists() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);

		String result = userService.updateRole(user.getUserId(), role);

		assertEquals("Role Updated Successfully!!!", result);
		verify(userRepository).findById(user.getUserId());
		verify(userRepository).save(user);
	}

	@Test
	public void testUpdateRole_UserNotFound() {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			userService.updateRole(user.getUserId(), role);
		});

		verify(userRepository).findById(user.getUserId());
	}

	@Test
	public void testAddUserEntity() {
		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = userService.addUserEntity(user);

		assertEquals(user, result);
		verify(userRepository).save(user);
	}

	@Test
	public void testFindByUsername() {
		when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

		Optional<User> result = userService.findByUsername(user.getUsername());

		assertEquals(Optional.of(user), result);
		verify(userRepository).findByUsername(user.getUsername());
	}

	@Test
	public void testExistsByUsername() {
		when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

		Boolean result = userService.existsByUsername(user.getUsername());

		assertEquals(true, result);
		verify(userRepository).existsByUsername(user.getUsername());
	}

	@Test
	public void testFindByRole() {
		when(userRepository.findByRole(role.getName())).thenReturn(Optional.of(user));

		Optional<User> result = userService.findByRole(role.getName());

		assertEquals(Optional.of(user), result);
		verify(userRepository).findByRole(role.getName());
	}
}
