package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.RoomType;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Room")
public class Room {
    @Id
    @JsonView(View.Room.class)
    private UUID idRoom;
    @Column(name = "idHostel", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private UUID idHostel;
    @Column(name = "Number room", unique = true, nullable = true)
    @JsonView(View.Room.class)
    private Integer numberRoom;
    @Column(name = "gender", unique = true, nullable = true)
    @JsonView(View.Room.class)
    private RoomType roomType;
    @Column(name = "Room size", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private Integer roomSize;

    @PrePersist
    public void generateId() {
        this.idRoom = UUID.randomUUID();
    }
}
