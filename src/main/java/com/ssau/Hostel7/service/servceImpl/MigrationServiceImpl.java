package com.ssau.Hostel7.service.servceImpl;

import com.ssau.Hostel7.exception.EntityNotFoundException;
import com.ssau.Hostel7.exception.RoomTypeMismatchException;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Profile;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.RoomMigration;
import com.ssau.Hostel7.model.enumModel.ConfirmationStatus;
import com.ssau.Hostel7.repository.HostelResidentRepository;
import com.ssau.Hostel7.repository.RoomMigrationRepository;
import com.ssau.Hostel7.repository.RoomRepository;
import com.ssau.Hostel7.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService {

    private final RoomMigrationRepository roomMigrationRepository;

    private final RoomRepository roomRepository;

    private final HostelResidentRepository hostelResidentRepository;

    private final ErrorMessagesHolder errorMessages;

    /**
     * Заселение или переселение в комнату (Занять новую комнату).
     * @param resident Сущность жильца.
     * @param room Сущность комнаты.
     */
    @Override
    public void startMigration(HostelResident resident, Room room) {
        if (room.getIdRoom() == null || resident.getId() == null) {
            throw new IllegalArgumentException("Entity not saved!");
        }
        Profile profile = resident.getProfile();
        if (room.getRoomType() != profile.getPreferredRoomType()) {
            throw new RoomTypeMismatchException(errorMessages.getRoomTypeMismatch());
        }

        RoomMigration roomMigration;
        roomMigration = new RoomMigration(
                null,
                new Date(),
                null,
                resident.getId(),
                room.getIdRoom(),
                ConfirmationStatus.inQueue
        );

        roomMigrationRepository.save(roomMigration);
    }

    @Override
    public void endMigration(UUID idHostelResident, UUID roomId)
    {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Room room = roomOpt.orElseThrow(() -> new EntityNotFoundException(
                errorMessages.getEntityNotFound()
        ));

        RoomMigration roomMigration = roomMigrationRepository.findByIdHostelResidentAndIdRoomAndEndDayIsNull(idHostelResident,room.getIdRoom());
        roomMigration.setEndDay(new Date());
        roomMigrationRepository.save(roomMigration);
    }
}
