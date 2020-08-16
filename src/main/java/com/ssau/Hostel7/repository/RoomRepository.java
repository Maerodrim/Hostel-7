package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.enumModel.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Repository
@Table(name = "Room")
public interface RoomRepository extends CrudRepository<Room, UUID> {
    Room findByIdHostelAndAndNumberRoom(UUID id,Integer number);

    @Query(
            "select " +
                "r.idRoom " +
            "from Room r " +
            "where " +
                "r.roomType = :room_type " +
                "and r.roomSize < (" +
                    "select count(m) from RoomMigration m where m.idRoom = r.idRoom and m.endDay is null" +
                ")"
    )
    List<UUID> findAllUnoccupiedRoomsByRoomType(@Param("room_type") RoomType roomType);
}
