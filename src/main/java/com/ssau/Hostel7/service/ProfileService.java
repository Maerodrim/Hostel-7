package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.request.ProfileRequestDto;
import com.ssau.Hostel7.dto.response.ProfileResponseDto;
import com.ssau.Hostel7.dto.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public interface ProfileService extends UserDetailsService {

    @Override
    CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    CustomUserDetails loadUserById(UUID id) throws UsernameNotFoundException;

    ProfileResponseDto register(ProfileRequestDto dto);

}
