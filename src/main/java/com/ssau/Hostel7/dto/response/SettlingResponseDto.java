package com.ssau.Hostel7.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.RoomType;
import com.ssau.Hostel7.model.enumModel.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SettlingResponseDto {

    @JsonProperty("id")
    private String id;

    private String name;

    private String surname;

    private String patronymic;

    private String login;

    private RoomType preferredRoomType;

    private Role role;

    private Status status;
    
}
