package com.ssau.Hostel7.service.security;

import com.ssau.Hostel7.dto.security.CustomUserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

public interface JwtAuthenticationService {

    String generateAuthToken(CustomUserDetails userDetails);

    Optional<UUID> getUserIdFromToken(String token);

    Optional<UUID> getUserIdFromRequest(HttpServletRequest request);

}
