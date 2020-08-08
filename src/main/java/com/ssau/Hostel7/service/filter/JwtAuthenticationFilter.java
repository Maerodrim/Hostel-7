package com.ssau.Hostel7.service.filter;

import com.ssau.Hostel7.dto.security.CustomAuthentication;
import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.service.ProfileService;
import com.ssau.Hostel7.service.security.JwtAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationService jwtAuthenticationService;

    private final ProfileService profileService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<UUID> userIdOpt = jwtAuthenticationService.getUserIdFromRequest(request);
        if (!userIdOpt.isPresent()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }
        UUID userId = userIdOpt.get();

        CustomUserDetails customUserDetails = profileService.loadUserById(userId);
        Authentication authentication = new CustomAuthentication(customUserDetails, true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
