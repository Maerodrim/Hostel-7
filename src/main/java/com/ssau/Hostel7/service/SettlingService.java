package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;

import java.util.Set;
import java.util.UUID;

public interface SettlingService {

    /**
     * Register new settler.
     * @param mail mail of settler profile.
     * @return registered settler.
     */
    SettlingResponseDto saveSettler(String mail);

    Set<CheckInQueueResponseDto> getSettlersQueue();

    SettlingResponseDto getLastSettlerInQueue();

    SettlingResponseDto getSettlerById(UUID id);

}
