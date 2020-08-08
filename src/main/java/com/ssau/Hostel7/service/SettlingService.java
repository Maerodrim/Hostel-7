package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.request.SettlerRequestDto;
import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;

import java.util.Set;
import java.util.UUID;

public interface SettlingService {

    /**
     * Register new settler.
     * @param dto request dto for registration.
     * @return registered settler.
     */
    SettlingResponseDto saveSettler(SettlerRequestDto dto);

    Set<CheckInQueueResponseDto> getSettlersQueue();

    SettlingResponseDto getSettlerById(UUID id);

}
