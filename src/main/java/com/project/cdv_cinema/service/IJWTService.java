package com.project.cdv_cinema.service;

public interface IJWTService {
    String generateToken(String email);
    String extractEmailFromToken(String token);
    boolean validateJwtToken(String authToken);
}
