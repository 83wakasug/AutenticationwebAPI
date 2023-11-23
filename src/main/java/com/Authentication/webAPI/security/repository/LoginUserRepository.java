package com.Authentication.webAPI.security.repository;

import com.Authentication.webAPI.security.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByEmail(String email);

}
