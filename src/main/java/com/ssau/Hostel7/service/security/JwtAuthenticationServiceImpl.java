package com.ssau.Hostel7.service.security;

import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.helper.holders.JwtConstantsHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

    private final JwtConstantsHolder jwtConstants;

    @Override
    public String generateAuthToken(CustomUserDetails userDetails) {
        Claims claims = new DefaultClaims();
        long issuedAtMs = new Date().getTime();
        long issuedAtSeconds = TimeUnit.MILLISECONDS.toSeconds(issuedAtMs);
        claims.put(Claims.ISSUED_AT, issuedAtSeconds);
        claims.put(Claims.NOT_BEFORE, issuedAtSeconds);

        long expirationAtMs = issuedAtMs + jwtConstants.getExpirationTimeMs();

        claims.put(userDetails.getId().toString(), jwtConstants.getIdJsonFieldName());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(expirationAtMs))
                .signWith(SignatureAlgorithm.HS512, jwtConstants.getJwtKey())
                .compact();
    }

    @Override
    public Optional<UUID> getUserIdFromToken(String token) {
        DefaultClaims claims = (DefaultClaims) Jwts.parser()
                .setSigningKey(jwtConstants.getJwtKey())
                .parse(token)
                .getBody();
        String id = claims.get(
                jwtConstants.getIdJsonFieldName(),
                String.class
        );
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(UUID.fromString(id));
    }

    @Override
    public Optional<UUID> getUserIdFromRequest(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtConstants.getCookieName());
        if (cookie == null) {
            return Optional.empty();
        }

        return getUserIdFromToken(cookie.getValue());
    }
}
