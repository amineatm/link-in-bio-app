package com.linkinbio.backend.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {

    public static CurrentUser get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CurrentUser) {
            return (CurrentUser) auth.getPrincipal();
        }
        throw new RuntimeException("Unauthorized access");
    }
}
