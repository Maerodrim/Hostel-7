package com.ssau.Hostel7.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;
import com.ssau.Hostel7.service.SettlingService;
import com.ssau.Hostel7.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

import static com.ssau.Hostel7.constants.UrlsConstants.*;

@RestController
@RequestMapping(SETTLING)
@RequiredArgsConstructor
public class SettlingController {

    private final SettlingService settlingService;

    @PostMapping
    public SettlingResponseDto settled(Principal principal) {
        String mail = principal.getName();
        return settlingService.saveSettler(mail);
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping(GET + QUEUE)
    public Set<CheckInQueueResponseDto> getQueueSettlingInDorms() {
        //TODO Pagination
        Set<CheckInQueueResponseDto> result = settlingService.getSettlersQueue();
        return result;
    }

    @JsonView(View.CheckInQueue.class)
    @GetMapping(GET + ID_PATH)
    public SettlingResponseDto getSettlingInDorms(@PathVariable("id") UUID id) {
        return settlingService.getSettlerById(id);
    }
}
