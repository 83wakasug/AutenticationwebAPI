package com.Authentication.webAPI.security.controller;

import com.Authentication.webAPI.security.dao.request.SignUpAdmin;
import com.Authentication.webAPI.security.dao.request.SignUpRequest;
import com.Authentication.webAPI.security.dao.request.SigningRequest;
import com.Authentication.webAPI.security.dao.response.JwtAuthenticationResponse;
import com.Authentication.webAPI.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    /**
     * Endpoint for user registration.
     * @param request SignUpRequest object containing user registration details.
     * @return ResponseEntity with a JwtAuthenticationResponse upon successful registration.
     */
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    /**
     * Endpoint for admin registration.
     * @param request SignUpAdmin object containing admin registration details.
     * @return ResponseEntity with a JwtAuthenticationResponse upon successful admin registration.
     */
    @PostMapping("/signupAdmin")
    public ResponseEntity<JwtAuthenticationResponse> signupAdmin(@RequestBody SignUpAdmin request) {
        return ResponseEntity.ok(authenticationService.signupAdmin(request));
    }

    /**
     * Endpoint for user sign-in.
     * @param request SigningRequest object containing user sign-in details.
     * @return ResponseEntity with a JwtAuthenticationResponse upon successful sign-in.
     */
    @PostMapping("/signingIn")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigningRequest request) {
        System.out.println(authenticationService.signin(request));
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}