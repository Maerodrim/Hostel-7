package com.ssau.Hostel7.helper;

import com.ssau.Hostel7.dto.request.ProfileRequestDto;
import com.ssau.Hostel7.dto.response.CheckInQueueResponseDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.dto.response.ProfileResponseDto;
import com.ssau.Hostel7.dto.response.RoomResponseDto;
import com.ssau.Hostel7.dto.response.SettlingResponseDto;
import com.ssau.Hostel7.dto.security.CustomUserDetails;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Status;

public interface DtoUtils {

    Profile getProfile(ProfileRequestDto dto);

    SettlingInDorms getSettling(
            Status status,
            Profile profile
    );

    SettlingResponseDto getSettlingResponseDto(SettlingInDorms settler);

    CheckInQueueResponseDto getCheckInQueueResponseDto(CheckInQueue entity);

    CustomUserDetails getCustomUserDetails(Profile profile);

    HostelResidentResponseDto getHostelResidentResponseDto(HostelResident hostelResident);

    ProfileResponseDto getProfileResponseDto(Profile profile);

    RoomResponseDto getRoomResponseDto(Room room);

}
