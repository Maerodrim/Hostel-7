package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.ConfirmationStatus;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "RoomMigration")
public class RoomMigration {
    @Id
    @JsonView(View.RoomMigration.class)
    private UUID idRoomMigration;
    @Column(name = "Start Day", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time startDay;
    @Column(name = "End Day", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time endDay;
    @Column(name = "Hostel Resident Id", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private UUID idHostelResident;
    @Column(name = "Room Id", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private UUID idRoom;
    @Column(name = "Сonfirmation", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private ConfirmationStatus status;

    @PrePersist
    public void generateId() {
        this.idRoomMigration = UUID.randomUUID();
    }
}
