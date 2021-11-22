package com.example.FinalProject.security;

import com.example.FinalProject.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityUser  implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();
        if (user.getRole().equals("user")){
            authoritiesSet.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        if (user.getRole().equals("manager")){
            authoritiesSet.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(),
                true,
                true,
                true,
                true,
                authoritiesSet
        );
    }
}
