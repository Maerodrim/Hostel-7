package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.constants.PatternsConstants;
import com.ssau.Hostel7.model.enumModel.PreferredRoomType;
import com.ssau.Hostel7.model.enumModel.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class ProfileRequestDto {

    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    private String id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("surname")
    @NotNull
    private String surname;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("login")
    @NotNull
    private String login;

    @JsonProperty( "role")
    @NotNull
    private Role role;

    @JsonProperty("preferred_room_type")
    @NotNull
    private PreferredRoomType preferredRoomType;
}
