package com.ssau.Hostel7.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.model.enumModel.PreferredRoomType;
import com.ssau.Hostel7.model.enumModel.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseDto {

    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty( "role")
    private Role role;

    @JsonProperty("preferred_room_type")
    private PreferredRoomType preferredRoomType;
}
