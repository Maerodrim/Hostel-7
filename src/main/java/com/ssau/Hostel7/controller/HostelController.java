package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.dto.request.HostelResidentRequestDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.service.HostelService;
import com.ssau.Hostel7.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssau.Hostel7.constants.UrlsConstants.*;

@RestController
@RequestMapping(HOSTEL)
@RequiredArgsConstructor
public class HostelController {

    private final HostelService hostelService;


    @JsonView(View.HostelResident.class)
    @PostMapping(REGISTRATION)
    public HostelResidentResponseDto residentRegistration(
            @RequestBody @Valid HostelResidentRequestDto dto
    ) {
        return hostelService.registerProfile(dto);
    }
}
