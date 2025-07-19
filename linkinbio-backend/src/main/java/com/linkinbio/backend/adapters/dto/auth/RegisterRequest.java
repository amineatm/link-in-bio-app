package com.linkinbio.backend.adapters.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
