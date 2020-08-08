package com.ssau.Hostel7.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@AllArgsConstructor
@Builder
public class CheckInQueueResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("settler")
    private SettlingResponseDto settler;

    @JsonProperty("time")
    private Time time;

    @JsonProperty("is_settled")
    private Boolean isSettled;

}
