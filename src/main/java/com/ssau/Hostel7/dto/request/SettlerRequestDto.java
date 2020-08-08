package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.model.enumModel.PreferredRoomType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SettlerRequestDto {

    @JsonProperty("group_number")
    private String groupNumber;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("surname")
    @NotNull
    private String surname;

    @JsonProperty("patronymic")
    @NotNull
    private String patronymic;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("login")
    @NotNull
    private String login;
    
    @JsonProperty("contact_number")
    @NotNull
    private String contactNumber;

    @JsonProperty("preferred_room_type")
    @NotNull
    private PreferredRoomType preferredRoomType;

}
