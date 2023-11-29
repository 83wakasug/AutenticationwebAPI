package com.Authentication.webAPI.security.config;

import com.Authentication.webAPI.security.jwt.JwtAuthenticationFilter;
import com.Authentication.webAPI.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    // JWT authentication filter for processing JWT tokens
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    // User service for retrieving user details
    private final UserService userService;

    /**
     * Configures the security filter chain.
     * @param http HttpSecurity object to configure security settings.
     * @return SecurityFilterChain configured with specified settings.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests(authz -> authz
                        // Allow public access to authentication-related endpoints
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        // Restrict access to certain endpoints based on user roles
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/auth/**").hasAnyRole("USER","ADMIN")
                        // Require authentication for any other endpoint
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                // Configure JWT authentication filter
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    /**
     * Configures the authentication provider.
     * @return AuthenticationProvider configured with user details service and password encoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configures the AuthenticationManager.
     * @param config AuthenticationConfiguration object.
     * @return AuthenticationManager configured with the provided AuthenticationConfiguration.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configures the password encoder.
     * @return PasswordEncoder using BCrypt hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
