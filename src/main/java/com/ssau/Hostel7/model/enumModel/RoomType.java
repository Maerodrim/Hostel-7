package com.ssau.Hostel7.model.enumModel;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoomType {
    MALE_ROOM("male"),
    FAMILY_ROOM("family"),
    FEMALE_ROOM("female");

    @JsonValue
    public String value;

}
