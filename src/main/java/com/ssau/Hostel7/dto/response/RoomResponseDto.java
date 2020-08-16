package com.ssau.Hostel7.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.model.enumModel.RoomType;
import lombok.Data;

@Data
public class RoomResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("hostel_id")
    private String hostelId;

    @JsonProperty("room_num")
    private Integer numberRoom;

    @JsonProperty("room_type")
    private RoomType roomType;

    @JsonProperty("room_size")
    private Integer roomSize;

}
