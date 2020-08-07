package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.Gender;
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
    @JsonView(View.CheckInQueue.class)
    private UUID idSettlingInDorms;
    @Column(name = "name", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private String name;
    @Column(name = "surname", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private String surname;
    @Column(name = "patronymic", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private String patronymic;
    @Column(name = "login", unique = true, nullable = true)
    @JsonView(View.HostelResident.class)
    private String login;
    @Column(name = "gender", unique = true, nullable = true)
    @JsonView(View.HostelResident.class)
    private Gender gender;
    @Column(name = "role", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private Role role;
    @Column(name = "status", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private Status status;
    @Column(name = "Group number", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private String GroupNumber;
    @Column(name = "Password", unique = false, nullable = true)
    @JsonView(View.Protected.class)
    private String password;

    @PrePersist
    public void generateId() {
        this.idSettlingInDorms = UUID.randomUUID();
    }
}
