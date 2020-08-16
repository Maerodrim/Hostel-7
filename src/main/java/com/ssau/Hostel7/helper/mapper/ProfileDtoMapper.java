package com.ssau.Hostel7.helper.mapper;

import com.ssau.Hostel7.dto.response.ProfileResponseDto;
import com.ssau.Hostel7.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ProfileDtoMapper {

    @Mapping(
            target = "id",
            expression = "java(profile.getId() == null " +
                    "? null " +
                    ": profile.getId().toString())"
    )
    ProfileResponseDto getResponseDto(Profile profile);

}
