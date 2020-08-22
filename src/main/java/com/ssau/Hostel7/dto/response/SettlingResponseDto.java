package com.ssau.Hostel7.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.RoomType;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SettlingResponseDto {

    @JsonProperty("id")
    private String id;
    
    @JsonView(View.CheckInQueue.class)
    private String name;
    
    @JsonView(View.CheckInQueue.class)
    private String surname;
    
    @JsonView(View.CheckInQueue.class)
    private String patronymic;
    
    @JsonView(View.HostelResident.class)
    private String login;
    
    @JsonView(View.HostelResident.class)
    private RoomType preferredRoomType;
    
    @JsonView(View.CheckInQueue.class)
    private Role role;
    
    @JsonView(View.CheckInQueue.class)
    private Status status;
    
}
