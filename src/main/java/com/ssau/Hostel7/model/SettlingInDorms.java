package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.model.enumModel.Status;
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
@Table(name = "SettlingInDorms")
public class SettlingInDorms {

    @Id
    @JsonView(View.HostelResident.class)
    private UUID idSettlingInDorms;
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
    @Column(name = "status", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Status status;
    @Column(name = "Group number", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private String GroupNumber;
    @Column(name = "Password", unique = false, nullable = true)
    @JsonView(View.Protected.class)
    private String password;

    @PrePersist
    public void generateId() {
        this.idSettlingInDorms = UUID.randomUUID();
    }
}
