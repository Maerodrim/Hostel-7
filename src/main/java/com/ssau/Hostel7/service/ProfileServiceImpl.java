package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.repository.ProfileRepository;
import com.ssau.Hostel7.helper.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ErrorMessagesHolder errorMessages;

    private final DtoUtils dtoUtils;

    private final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Profile> profileOpt = profileRepository.findByLogin(username);
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

}
