package com.ssau.Hostel7.service;

import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Room;

import java.util.UUID;

public interface MigrationService {

    void startMigration(HostelResident resident, Room room);

    void endMigration(UUID idHostelResident, UUID roomId);

}
