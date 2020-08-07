package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.view.View;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Hostel")
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Hostel.class)
    private Integer idHostel;
    @Column(name = "number", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Integer number;
    @OneToMany
    @Column(name = "personal", unique = false, nullable = true)
    @JsonView(View.Personal.class)
    private Set<Personal> user;
    @OneToMany
    @Column(name = "room", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private Set<Room> room;
}
