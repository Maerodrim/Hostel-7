package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.RoomType;
import com.ssau.Hostel7.model.enumModel.Role;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Сущность профиля.
 */
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    /**
     * Идентификатор профиля.
     */
    @Id
    private UUID id;

    /**
     * Имя.
     */
    @Column(name = "name", nullable = false)
    @JsonView(View.HostelResident.class)
    private String name;

    /**
     * Фамилия.
     */
    @Column(name = "surname", nullable = false)
    @JsonView(View.HostelResident.class)
    private String surname;

    /**
     * Отчество.
     */
    @Column(name = "patronymic")
    @JsonView(View.HostelResident.class)
    private String patronymic;

    /**
     * Логин.
     */
    @Column(name = "mail", nullable = false)
    @JsonView(View.HostelResident.class)
    private String mail;

    /**
     * Тип комнаты по половой принадлежности.
     */
    @Column(name = "preferred_room_type", nullable = false)
    @JsonView(View.HostelResident.class)
    private RoomType preferredRoomType;

    /**
     * Поролб.
     */
    @Column(name = "Password", nullable = false)
    @JsonView(View.Protected.class)
    private String password;

    /**
     * Роль.
     */
    @Column(name = "role", nullable = false)
    @JsonView(View.HostelResident.class)
    private Role role;

    /**
     * Сгенерировать идентификатор.
     */
    @PrePersist
    public void generateId() {
        id = UUID.randomUUID();
    }

}
