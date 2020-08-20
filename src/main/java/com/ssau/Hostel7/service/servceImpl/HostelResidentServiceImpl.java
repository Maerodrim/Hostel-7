package com.ssau.Hostel7.service.servceImpl;

import com.ssau.Hostel7.dto.request.HostelResidentRequestDto;
import com.ssau.Hostel7.dto.response.HostelResidentResponseDto;
import com.ssau.Hostel7.exception.EntityNotFoundException;
import com.ssau.Hostel7.helper.DtoUtils;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.SettlingInDorms;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.repository.CheckInQueueRepository;
import com.ssau.Hostel7.repository.HostelResidentRepository;
import com.ssau.Hostel7.repository.RoomRepository;
import com.ssau.Hostel7.repository.SettlingInDormsRepository;
import com.ssau.Hostel7.service.HostelResidentService;
import com.ssau.Hostel7.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HostelResidentServiceImpl implements HostelResidentService {

    private final HostelResidentRepository hostelResidentRepository;

    private final CheckInQueueRepository checkInQueueRepository;

    private final SettlingInDormsRepository settlingInDormsRepository;

    private final RoomRepository roomRepository;

    private final MigrationService migrationService;

    private final DtoUtils dtoUtils;

    private final ErrorMessagesHolder errorMessages;

    private final Logger logger = LoggerFactory.getLogger(HostelResidentServiceImpl.class);

    @Override
    public HostelResidentResponseDto registerResident(HostelResidentRequestDto dto, String roomId) {
        UUID settlerId = UUID.fromString(dto.getSettlerId());
        Optional<SettlingInDorms> settlerOpt = settlingInDormsRepository.findById(settlerId);
        SettlingInDorms settler = settlerOpt.orElseThrow(() ->
                new EntityNotFoundException(errorMessages.getEntityNotFound())
        );
        Profile profile = settler.getProfile();

        UUID roomIdUUID = UUID.fromString(roomId);
        Optional<Room> roomOptional = roomRepository.findById(roomIdUUID);
        Room room = roomOptional.orElseThrow(() ->
                new EntityNotFoundException(errorMessages.getEntityNotFound())
        );

        HostelResident hostelResident = new HostelResident(
                null,
                dto.getStudentIDNumber(),
                dto.getContractIdNumber(),
                dto.getGroupNumber(),
                dto.getPassNumber(),
                profile
        );

        logger.trace("Registration resident {}", hostelResident);

        HostelResident saved = hostelResidentRepository.save(hostelResident);

        CheckInQueue checkInQueue = checkInQueueRepository.findBySettler(settler);
        checkInQueue.setIsSettled(true);
        checkInQueueRepository.save(checkInQueue);

        migrationService.startMigration(saved, room);

        settler.setStatus(Status.Occupied);
        settlingInDormsRepository.save(settler);

        return dtoUtils.getHostelResidentResponseDto(saved);
    }

}
