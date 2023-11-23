package com.Authentication.webAPI.security.service;

package com.api.webservice.security.service;


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


    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {

        Roles role = roleRepository.findByAuthority(Role.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Roles(Role.ROLE_USER)));

        var user = LoginUser.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>(Collections.singletonList(role))).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signupAdmin(SignUpAdmin request) {

        Roles role = roleRepository.findByAuthority(Role.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Roles(Role.ROLE_ADMIN)));
        var user = LoginUser.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>(Collections.singletonList(role))).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    @Override
    public JwtAuthenticationResponse signin(SigningRequest request) {
        System.out.println(request.getEmail());
        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }





}
