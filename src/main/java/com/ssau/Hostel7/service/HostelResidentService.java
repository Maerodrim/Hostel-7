package com.ssau.Hostel7.service;

import com.ssau.Hostel7.dto.request.HostelResidentRequestDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;

public interface HostelResidentService {

    HostelResidentResponseDto registerResident(HostelResidentRequestDto dto, String roomId);

}
