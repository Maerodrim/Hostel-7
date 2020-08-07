package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.view.View;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Builder
@NoArgsConstructor
@Setter
@Getter
@Table(name = "RoomMigration")
public class RoomMigration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.RoomMigration.class)
    private Integer idRoomMigration;
    @Column(name = "Start Day", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time startDay;
    @Column(name = "End Day", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time endDay;
    @Column(name = "Hostel Resident Id", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Integer idHostelResident;
    @Column(name = "Room Id", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Integer idRoom;
}
