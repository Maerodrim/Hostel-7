package com.ssau.Hostel7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "RoomMigration")
public class RoomMigration {
    @Id
    private UUID idRoomMigration;
    @Column(name = "Start_Day", unique = false, nullable = true)
    private Date startDay;
    @Column(name = "End_Day", unique = false, nullable = true)
    private Date endDay;
    @Column(name = "Hostel_Resident_Id", unique = false, nullable = true)
    private UUID idHostelResident;
    @Column(name = "Room_Id", unique = false, nullable = true)
    private UUID idRoom;

    @PrePersist
    public void generateId() {
        this.idRoomMigration = UUID.randomUUID();
    }
}
