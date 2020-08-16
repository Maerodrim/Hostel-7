package com.ssau.Hostel7.controller;

import com.ssau.Hostel7.constants.UrlsConstants;
import com.ssau.Hostel7.dto.request.ProfileRequestDto;
import com.ssau.Hostel7.dto.response.ProfileResponseDto;
import com.ssau.Hostel7.helper.validation.group.CreateProfileValidationGroup;
import com.ssau.Hostel7.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(UrlsConstants.REGISTRATION)
    public ProfileResponseDto registerProfile(
            @RequestBody
            @Validated(CreateProfileValidationGroup.class)
                    ProfileRequestDto dto
    ) {
        ProfileResponseDto registered = profileService.register(dto);
        return registered;
    }

}
