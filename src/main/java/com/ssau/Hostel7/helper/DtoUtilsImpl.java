package com.ssau.Hostel7.helper;

import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.dto.request.SettlerRequestDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DtoUtilsImpl implements DtoUtils {

    @Override
    public Profile getProfile(SettlerRequestDto dto, Role role) {
        Profile profile = new Profile(
                null,
                dto.getName(),
                dto.getSurname(),
                dto.getPatronymic(),
                dto.getLogin(),
                dto.getPreferredRoomType(),
                dto.getPassword(),
                role
        );

        return profile;
    }

    @Override
    public SettlingInDorms getSettling(
            Status status,
            Profile profile
    ) {
        return new SettlingInDorms(
                null,
                status,
                profile
        );
    }

    @Override
    @Transactional(readOnly = true)
    public SettlingResponseDto getSettlingResponseDto(SettlingInDorms settler) {
        Profile profile = settler.getProfile();

        return SettlingResponseDto.builder()
                .preferredRoomType(profile.getPreferredRoomType())
                .id(
                        settler.getId() == null
                                ? null
                                : settler.getId().toString()
                )
                .login(profile.getLogin())
                .name(profile.getName())
                .password(profile.getPassword())
                .patronymic(profile.getPatronymic())
                .role(profile.getRole())
                .status(settler.getStatus())
                .surname(profile.getSurname())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public CheckInQueueResponseDto getCheckInQueueResponseDto(
            CheckInQueue entity
    ) {
        SettlingInDorms settler = entity.getSettler();
        SettlingResponseDto settlerDto = getSettlingResponseDto(settler);

        CheckInQueueResponseDto result = CheckInQueueResponseDto.builder()
                .id(
                        entity.getId() == null
                                ? null
                                : entity.getId().toString()
                )
                .settler(settlerDto)
                .isSettled(entity.getIsSettled())
                .time(entity.getTime())
                .build();
        return result;
    }

    @Override
    public CustomUserDetails getCustomUserDetails(Profile profile) {
        CustomUserDetails result = CustomUserDetails.builder()
                .id(profile.getId())
                .login(profile.getLogin())
                .passwordHash(profile.getPassword())
                .role(profile.getRole().name())
                .build();
        return result;
    }
}
