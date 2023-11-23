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

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<JwtAuthenticationResponse> signupAdmin(@RequestBody SignUpAdmin request) {
        return ResponseEntity.ok(authenticationService.signupAdmin(request));
    }

    @PostMapping("/signingIn")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigningRequest request) {
        System.out.println(authenticationService.signin(request));
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}