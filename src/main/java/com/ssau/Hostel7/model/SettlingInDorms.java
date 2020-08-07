package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.role.Role;
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
@Table(name = "SettlingInDorms")
public class SettlingInDorms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.HostelResident.class)
    private Integer idSettlingInDorms;
    @Column(name = "name", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private String name;
    @Column(name = "surname", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private String surname;
    @Column(name = "patronymic", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private String patronymic;
    @Column(name = "role", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Role role;
    @Column(name = "Group number", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private String GroupNumber;
}
