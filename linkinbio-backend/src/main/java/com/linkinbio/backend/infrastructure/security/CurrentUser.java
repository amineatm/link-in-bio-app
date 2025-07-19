package com.linkinbio.backend.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class CurrentUser implements UserDetails {
    private final String id;
    private final String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // No roles for now
    }

    @Override
    public String getPassword() {
        return null; // Not needed for context access
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
