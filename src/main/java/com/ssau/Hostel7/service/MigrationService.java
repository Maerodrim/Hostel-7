package com.ssau.Hostel7.service;

import com.google.inject.Singleton;
import com.ssau.Hostel7.exception.EntityNotFoundException;
import com.ssau.Hostel7.helper.holders.ErrorMessagesHolder;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.RoomMigration;
import com.ssau.Hostel7.model.enumModel.ConfirmationStatus;
import com.ssau.Hostel7.repository.RoomMigrationRepository;
import com.ssau.Hostel7.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class MigrationService {

    private final RoomMigrationRepository roomMigrationRepository ;
    private final RoomRepository roomRepository;
    private final ErrorMessagesHolder errorMessages;

    /**
     * Заселение или переселение в комнату.
     * @param idHostelResident Идентификатор жильца.
     * @param roomId Идентификатор комнаты.
     */
    public void startMigration(UUID idHostelResident, UUID roomId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Room room = roomOpt.orElseThrow(() -> new EntityNotFoundException(
                errorMessages.getEntityNotFound()
        ));

        RoomMigration roomMigration;
        roomMigration = new RoomMigration(null, Time.valueOf(String.valueOf(LocalDateTime.now())),
                null, idHostelResident, room.getIdRoom(), ConfirmationStatus.inQueue);

        roomMigrationRepository.save(roomMigration);
    }

    public void endMigration(UUID idHostelResident, UUID roomId)
    {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Room room = roomOpt.orElseThrow(() -> new EntityNotFoundException(
                errorMessages.getEntityNotFound()
        ));

        RoomMigration roomMigration = roomMigrationRepository.findByIdHostelResidentAndIdRoomAAndEndDayNull(idHostelResident,room.getIdRoom());
        roomMigration.setEndDay(Time.valueOf(String.valueOf(LocalDateTime.now())));
        roomMigrationRepository.save(roomMigration);
    }
}
