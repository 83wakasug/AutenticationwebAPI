package com.Authentication.webAPI.security.entity;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class LoginUser implements UserDetails {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role_junction",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )

    private Set<Roles> roles;


    /**
     * Set the roles associated with the user.
     * @param roles Set of Roles assigned to the user.
     */
    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    /**
     * Get the authorities granted to the user.
     * @return Collection of GrantedAuthority objects based on user roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority().name()))
                .collect(Collectors.toSet());
    }

    /**
     * Get the username of the user.
     * @return User's email as the username.
     */
    @Override
    public String getUsername() {
        return email;
    }
    /**
     * Check if the user account is non-expired.
     * @return Always returns false (custom implementation needed).
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }


    /**
     * Check if the user account is non-locked.
     * @return Always returns false (custom implementation needed).
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    /**
     * Check if user credentials are non-expired.
     * @return Always returns false (custom implementation needed).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Check if the user account is enabled.
     * @return Always returns false (custom implementation needed).
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
