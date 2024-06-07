package com.training.liscenselifecycletracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.security.jwt.JwtUtils;
import com.training.liscenselifecycletracker.security.payload.request.LoginRequest;
import com.training.liscenselifecycletracker.security.payload.request.SignupRequest;
import com.training.liscenselifecycletracker.security.payload.response.JwtResponse;
import com.training.liscenselifecycletracker.security.payload.response.MessageResponse;
import com.training.liscenselifecycletracker.security.service.UserDetailsImpl;
import com.training.liscenselifecycletracker.security.service.UserDetailsServiceImpl;
import com.training.liscenselifecycletracker.service.RoleService;
import com.training.liscenselifecycletracker.service.UserService;

class AuthControllerTest {

    private AuthController authController;

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserDetailsServiceImpl userDetailsService;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        jwtUtils = mock(JwtUtils.class);
        userDetailsService = mock(UserDetailsServiceImpl.class);
        userService = mock(UserService.class);
        roleService = mock(RoleService.class);
        passwordEncoder = mock(PasswordEncoder.class);

        authController = new AuthController();
        authController.authenticationManager = authenticationManager;
        authController.jwtUtils = jwtUtils;
        authController.userDetailsService = userDetailsService;
        authController.userService = userService;
        authController.roleService = roleService;
        authController.passwordEncoder = passwordEncoder;
    }

    @Test
    void testAuthenticateUser() {
        // Mocking
        Authentication authentication = mock(Authentication.class);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        UserDetailsImpl userDetails = new UserDetailsImpl(1L, "test_user", "test_user@example.com", "password", authority);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("test_token");

        // Test
        LoginRequest loginRequest = new LoginRequest("test_user", "password");
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test_token", ((JwtResponse) response.getBody()).getAccessToken());
    }

    @Test
    void testRegisterUser() {
        // Mocking
        SignupRequest signupRequest = new SignupRequest("new_user", "password", "new_user@example.com");
        when(userService.existsByUsername("new_user")).thenReturn(false);
        when(roleService.findRoleById(2)).thenReturn(java.util.Optional.of(new Role()));

        // Test
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully!", ((MessageResponse) response.getBody()).getMessage());
    }

    @Test
    void testRegisterUserExistingUsername() {
        // Mocking
        SignupRequest signupRequest = new SignupRequest("existing_user", "password", "existing_user@example.com");
        when(userService.existsByUsername("existing_user")).thenReturn(true);

        // Test
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assertion
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username is already taken!", ((MessageResponse) response.getBody()).getMessage());
    }
}
