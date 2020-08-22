package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.request.RegisterResidentRequestDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;

public interface HostelResidentService {

    HostelResidentResponseDto registerResident(RegisterResidentRequestDto dto, String roomId);

}
