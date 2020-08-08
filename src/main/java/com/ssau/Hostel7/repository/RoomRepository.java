package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.UUID;

@Repository
@Table(name = "Room")
public interface RoomRepository extends CrudRepository<Room, UUID> {
    Room findByIdHostelAndAndNumberRoom(UUID id,Integer number);
}
