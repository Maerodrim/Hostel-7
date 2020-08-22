package com.ssau.Hostel7.controller;

import com.ssau.Hostel7.constants.UrlsConstants;
import com.ssau.Hostel7.dto.response.RoomResponseDto;
import com.ssau.Hostel7.model.enumModel.RoomType;
import com.ssau.Hostel7.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping(UrlsConstants.RANDOM_SPARE_ROOM_URL + "/{type}")
    public RoomResponseDto getRandomSpareRoom(@PathVariable("type") RoomType type) {
        return roomService.getRandomSpareRoom(type);
    }

}
