package com.linkinbio.backend.domain.service;

import com.linkinbio.backend.adapters.dto.auth.TokenResponse;
import com.linkinbio.backend.domain.model.User;

public interface IAuthService {
    TokenResponse register(String email, String password);
    TokenResponse  login(String email, String password);
}
