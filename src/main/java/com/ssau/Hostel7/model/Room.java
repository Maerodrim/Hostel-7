package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.view.View;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Room.class)
    private Integer idRoom;
    @Column(name = "idHostel", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private Integer idHostel;
    @Column(name = "numberRoom", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private Integer numberRoom;
}
