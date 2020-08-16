package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.response.RoomResponseDto;
import com.ssau.Hostel7.model.enumModel.RoomType;

public interface RoomService {

    RoomResponseDto getRandomRoom(RoomType roomType);

}
