package com.Authentication.webAPI.security.service;



import com.Authentication.webAPI.security.dao.request.SignUpAdmin;
import com.Authentication.webAPI.security.dao.request.SignUpRequest;
import com.Authentication.webAPI.security.dao.request.SigningRequest;
import com.Authentication.webAPI.security.dao.response.JwtAuthenticationResponse;
import com.Authentication.webAPI.security.entity.LoginUser;
import com.Authentication.webAPI.security.entity.Role;
import com.Authentication.webAPI.security.entity.Roles;
import com.Authentication.webAPI.security.repository.LoginUserRepository;
import com.Authentication.webAPI.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final LoginUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;


    /**
     * Registers a new user based on the provided sign-up request.
     * Creates a user with ROLE_USER authority and generates a JWT token.
     * @param request SignUpRequest object containing user registration details.
     * @return JwtAuthenticationResponse with the generated JWT token.
     */
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Retrieve or create the ROLE_USER authority
        Roles role = roleRepository.findByAuthority(Role.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Roles(Role.ROLE_USER)));
        // Create a new user with the provided details and ROLE_USER authority
        var user = LoginUser.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>(Collections.singletonList(role))).build();
        // Save the user in the repository
        userRepository.save(user);

        // Generate a JWT token for the user
        var jwt = jwtService.generateToken(user);

        // Return the generated JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /**
     * Registers a new admin based on the provided admin sign-up request.
     * Creates a user with ROLE_ADMIN authority and generates a JWT token.
     * @param request SignUpAdmin object containing admin registration details.
     * @return JwtAuthenticationResponse with the generated JWT token.
     */
    @Override
    public JwtAuthenticationResponse signupAdmin(SignUpAdmin request) {
        // Retrieve or create the ROLE_ADMIN authority
        Roles role = roleRepository.findByAuthority(Role.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Roles(Role.ROLE_ADMIN)))

        // Create a new admin user with the provided details and ROLE_ADMIN authority;
        var user = LoginUser.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>(Collections.singletonList(role))).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /**
     * Authenticates a user based on the provided sign-in request.
     * Uses Spring Security's authentication manager to validate credentials.
     * Generates a JWT token upon successful authentication.
     * @param request SigningRequest object containing user sign-in details.
     * @return JwtAuthenticationResponse with the generated JWT token.
     * @throws IllegalArgumentException if the provided email or password is invalid.
     */
    @Override
    public JwtAuthenticationResponse signin(SigningRequest request) {
        // Log the email from the sign-in request (optional)
        System.out.println(request.getEmail());
        // Authenticate the user using Spring Security's authentication manager
        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        // Retrieve the authenticated user from the repository
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        // Generate a JWT token for the authenticated user
        var jwt = jwtService.generateToken(user);
        // Return the generated JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }





}
