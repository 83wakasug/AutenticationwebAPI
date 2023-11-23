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


    @Override
    public UserDetailsService userDetailsService() {
        return email -> {
            LoginUser user = repository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return User.builder()
                    .username(user.getEmail()) // ユーザー名には email を使用
                    .password(user.getPassword())
                    .authorities(user.getAuthorities()) // ロールを適切に設定
                    .build();
        };
    }

}

