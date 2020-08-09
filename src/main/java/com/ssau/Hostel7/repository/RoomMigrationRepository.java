package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.RoomMigration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.HashSet;
import java.util.UUID;

@Repository
@Table(name = "RoomMigration")
public interface RoomMigrationRepository extends CrudRepository<RoomMigration, UUID> {
    RoomMigration findByIdHostelResidentAndIdRoomAAndEndDayNull(UUID id, UUID idRoom);
    HashSet<RoomMigration> findByIdHostelResident(UUID id);
    HashSet<RoomMigration> findByEndDayIsNull();
}
