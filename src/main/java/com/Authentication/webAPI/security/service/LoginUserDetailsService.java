package com.Authentication.webAPI.security.service;

import com.Authentication.webAPI.security.entity.LoginUser;
import com.Authentication.webAPI.security.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserService {

    private final LoginUserRepository repository;

    /**
     * Retrieves user details based on the provided email.
     * Used by Spring Security for authentication and authorization.
     * @return UserDetailsService that loads user details from the repository.
     */
    @Override
    public UserDetailsService userDetailsService() {
        // Lambda expression defining a UserDetailsService for the given email
        return email -> {
            // Retrieve a user from the repository based on the provided email
            LoginUser user = repository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            // Build and return a UserDetails object with the user's information
            return User.builder()
                    .username(user.getEmail()) // Use email as the username
                    .password(user.getPassword())
                    .authorities(user.getAuthorities()) // Set authorities (roles) appropriately
                    .build();
        };
    }

}

