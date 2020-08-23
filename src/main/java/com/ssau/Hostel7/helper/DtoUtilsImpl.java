package com.ssau.Hostel7.helper;

import com.ssau.Hostel7.dto.request.ProfileRequestDto;
import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.dto.response.ProfileResponseDto;
import com.ssau.Hostel7.dto.response.RoomResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;
import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.helper.mapper.HostelResidentMapper;
import com.ssau.Hostel7.helper.mapper.ProfileDtoMapper;
import com.ssau.Hostel7.helper.mapper.RoomDtoMapper;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DtoUtilsImpl implements DtoUtils {

    private final HostelResidentMapper hostelResidentMapper;

    private final ProfileDtoMapper profileDtoMapper;

    private final RoomDtoMapper roomDtoMapper;

    @Override
    public Profile getProfile(ProfileRequestDto dto, Role role) {
        UUID id = dto.getId() == null ? null : UUID.fromString(dto.getId());
        Profile profile = new Profile(
                id,
                dto.getName(),
                dto.getSurname(),
                dto.getPatronymic(),
                dto.getMail(),
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
                .login(profile.getMail())
                .name(profile.getName())
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
    @Transactional(readOnly = true)
    public CustomUserDetails getCustomUserDetails(Profile profile) {
        CustomUserDetails result = CustomUserDetails.builder()
                .id(profile.getId())
                .login(profile.getMail())
                .passwordHash(profile.getPassword())
                .role(profile.getRole().getPrefixedValue())
                .build();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public HostelResidentResponseDto getHostelResidentResponseDto(HostelResident hostelResident) {
        return hostelResidentMapper.getResponseDto(hostelResident);
    }

    @Override
    public ProfileResponseDto getProfileResponseDto(Profile profile) {
        return profileDtoMapper.getResponseDto(profile);
    }

    @Override
    public RoomResponseDto getRoomResponseDto(Room room) {
        return roomDtoMapper.getResponseDto(room);
    }
}
