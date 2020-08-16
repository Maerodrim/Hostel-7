package com.ssau.Hostel7.helper.mapper;

import com.ssau.Hostel7.dto.response.RoomResponseDto;
import com.ssau.Hostel7.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface RoomDtoMapper {

    @Mapping(
            target = "id",
            expression = "java(room.getIdRoom() == null " +
                    "? null " +
                    ": room.getId().toString())"
    )
    @Mapping(
            target = "hostelId",
            expression = "java(room.getIdHostel() == null " +
                    "? null " +
                    ": room.getIdHostel().toString())"
    )
    RoomResponseDto getResponseDto(Room room);

}
