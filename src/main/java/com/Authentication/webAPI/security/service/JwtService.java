package com.Authentication.webAPI.security.service;

import com.Authentication.webAPI.security.entity.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(LoginUser LoginUser);

    boolean isTokenValid(String token, UserDetails userDetails);

    Set<String> extractRoles(String token);
}
