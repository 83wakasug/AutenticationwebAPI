package com.Authentication.webAPI.security.service;


import com.Authentication.webAPI.security.dao.request.SignUpAdmin;
import com.Authentication.webAPI.security.dao.request.SignUpRequest;
import com.Authentication.webAPI.security.dao.request.SigningRequest;
import com.Authentication.webAPI.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signupAdmin(SignUpAdmin request);
    JwtAuthenticationResponse signin(SigningRequest request);
}
