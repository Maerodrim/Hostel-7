package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.constants.PatternsConstants;
import com.ssau.Hostel7.helper.validation.group.CreateProfileValidationGroup;
import com.ssau.Hostel7.helper.validation.group.UpdateProfileValidationGroup;
import com.ssau.Hostel7.model.enumModel.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class ProfileRequestDto {

    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    @NotNull(groups = UpdateProfileValidationGroup.class)
    @Null(groups = CreateProfileValidationGroup.class)
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

    @JsonProperty("mail")
    @NotNull
    private String mail;

    @JsonProperty("preferred_room_type")
    @NotNull
    private RoomType preferredRoomType;
}
