package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.constants.PatternsConstants;
import com.ssau.Hostel7.model.enumModel.PreferredRoomType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Dto заселяемого.
 */
@Data
public class SettlerRequestDto {

    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    private String id;

    @JsonProperty("group_number")
    private String groupNumber;

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
    
    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("preferred_room_type")
    @NotNull
    private PreferredRoomType preferredRoomType;

}
