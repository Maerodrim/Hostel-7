package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.dto.request.SettlerRequestDto;
import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;
import com.ssau.Hostel7.service.SettlingService;
import com.ssau.Hostel7.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("SettlingController")
@RequiredArgsConstructor
public class SettlingController {

    private final SettlingService settlingService;

    @JsonView(View.HostelResident.class)
    @PostMapping("registrationSettlingInDorms")
    public SettlingResponseDto registrationSettlingInDorms(
            @RequestBody @Valid SettlerRequestDto settlerRequestDto
    ) {
        SettlingResponseDto result = settlingService.saveSettler(settlerRequestDto);

        return result;
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping("getQueueSettlingInDorms")
    public Set<CheckInQueueResponseDto> getQueueSettlingInDorms() {
        //TODO Pagination
        Set<CheckInQueueResponseDto> result = settlingService.getSettlersQueue();
        return result;
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping("getSettlingInDorms/{id}")
    public SettlingResponseDto getSettlingInDorms(@PathVariable("id") UUID id) {
        return settlingService.getSettlerById(id);
    }
}
