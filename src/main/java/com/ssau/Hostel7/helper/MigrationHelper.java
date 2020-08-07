package com.ssau.Hostel7.helper;

import com.google.inject.Singleton;
import com.ssau.Hostel7.model.Hostel;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.RoomMigration;
import com.ssau.Hostel7.repository.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Singleton
@NoArgsConstructor
public class MigrationHelper{

    private RoomMigrationRepository roomMigrationRepository ;
    private RoomRepository roomRepository;
    private HostelRepository hostelRepository;

    @Autowired
    public MigrationHelper(RoomMigrationRepository roomMigrationRepository,
                           RoomRepository roomRepository,
                           HostelRepository hostelRepository) {
        this.roomMigrationRepository  = roomMigrationRepository ;
        this.hostelRepository = hostelRepository;
        this.roomRepository = roomRepository;
    }

    public void startMigration(UUID idHostelResident, Integer numberRoom, Integer numberHostel)
    {
        Hostel hostel = hostelRepository.findByNumber(numberHostel);
        Room room = roomRepository.findByIdHostelAndAndNumberRoom(hostel.getIdHostel(),numberRoom);

        RoomMigration roomMigration = new RoomMigration(null, Time.valueOf(String.valueOf(LocalDateTime.now())),
                null,idHostelResident,room.getIdRoom());
        roomMigrationRepository.save(roomMigration);
    }

    public void endMigration(UUID idHostelResident, Integer numberRoom, Integer numberHostel)
    {
        Hostel hostel = hostelRepository.findByNumber(numberHostel);
        Room room = roomRepository.findByIdHostelAndAndNumberRoom(hostel.getIdHostel(),numberRoom);

        RoomMigration roomMigration = roomMigrationRepository.findByIdHostelResidentAndIdRoom(idHostelResident,room.getIdRoom());
        roomMigration.setEndDay(Time.valueOf(String.valueOf(LocalDateTime.now())));
        roomMigrationRepository.save(roomMigration);
    }
}
