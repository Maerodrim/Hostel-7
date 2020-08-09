package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.constants.PatternsConstants;
import com.ssau.Hostel7.dto.request.HostelResidentRequestDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.service.HostelResidentService;
import com.ssau.Hostel7.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping("ResidentController")
@RequiredArgsConstructor
public class ResidentController {

    private final HostelResidentService hostelResidentService;


    @JsonView(View.HostelResident.class)
    @PostMapping("residentRegistration/{room_id}")
    public HostelResidentResponseDto residentRegistration(
            @RequestBody @Valid HostelResidentRequestDto dto,
            @PathVariable("room_id") @Pattern(regexp = PatternsConstants.UUID_PATTERN) String roomId
    ) {
         return hostelResidentService.registerResident(dto, roomId);
    }
}
