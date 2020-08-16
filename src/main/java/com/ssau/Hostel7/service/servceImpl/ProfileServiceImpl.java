package com.ssau.Hostel7.service.servceImpl;

import com.ssau.Hostel7.dto.request.ProfileRequestDto;
import com.ssau.Hostel7.dto.response.ProfileResponseDto;
import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.helper.DtoUtils;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.repository.ProfileRepository;
import com.ssau.Hostel7.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ErrorMessagesHolder errorMessages;

    private final DtoUtils dtoUtils;

    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Profile> profileOpt = profileRepository.findByMail(username);
        Profile profile = profileOpt.orElseThrow(() -> {
            UsernameNotFoundException throwed = new UsernameNotFoundException(
                    errorMessages.getEntityNotFound()
            );
            logger.warn("User not found!", throwed);
            return throwed;
        });

        return dtoUtils.getCustomUserDetails(profile);
    }

    @Override
    public CustomUserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        Profile profile = profileOpt.orElseThrow(() -> {
            UsernameNotFoundException throwed = new UsernameNotFoundException(
                    errorMessages.getEntityNotFound()
            );
            logger.warn("User not found!", throwed);
            return throwed;
        });

        return dtoUtils.getCustomUserDetails(profile);
    }

    @Override
    public ProfileResponseDto register(ProfileRequestDto dto) {
        Profile profile = dtoUtils.getProfile(dto);
        profile.setRole(getDefaultRole());

        //Сначала кодируем пароль, потом проверяем существование пользователя.
        String encodedPas = passwordEncoder.encode(profile.getPassword());
        profile.setPassword(encodedPas);

        boolean existsByLogin = profileRepository.existsByMail(profile.getMail());
        if (existsByLogin) {
            return dtoUtils.getProfileResponseDto(profile);
        }

        Profile saved = profileRepository.save(profile);
        return dtoUtils.getProfileResponseDto(saved);
    }

    private Role getDefaultRole() {
        return Role.Registered;
    }
}
