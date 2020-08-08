package com.ssau.Hostel7.service.security;

import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.helper.holders.JwtConstantsHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtAuthenticationService jwtAuthenticationService;

    private final JwtConstantsHolder jwtConstants;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomUserDetails)) {
            return;
        }
        CustomUserDetails userDetails = (CustomUserDetails) principal;

        String token = jwtAuthenticationService.generateAuthToken(userDetails);
        long maxAge = TimeUnit.MILLISECONDS.toSeconds(
                jwtConstants.getExpirationTimeMs()
        );
        ResponseCookie cookie = ResponseCookie
                .from(jwtConstants.getCookieName(), token)
                .maxAge(maxAge)
                .httpOnly(true)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        response.setHeader(HttpHeaders.LOCATION, "/");
        response.setStatus(HttpStatus.PERMANENT_REDIRECT.value());
    }
}
