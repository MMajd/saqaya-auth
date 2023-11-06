package com.saqaya.adapter.in.web.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserId(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}