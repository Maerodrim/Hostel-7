package com.ssau.Hostel7.helper.mapper;


import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.model.HostelResident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface HostelResidentMapper {

    @Mapping(
            target = "id",
            expression = "java(hostelResident.getId() == null " +
                    "? null " +
                    ": hostelResident.getId().toString())"
    )
    @Mapping(
            target = "profileId",
            expression = "java(hostelResident.getProfile() == null " +
                    "? null " +
                    ": hostelResident.getProfile().getId().toString())"
    )
    HostelResidentResponseDto getResponseDto(HostelResident hostelResident);

}
