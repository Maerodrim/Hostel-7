package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.PreferredRoomType;
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
@Table(name = "Profile")
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
    @Column(name = "name", unique = false, nullable = true)
    @JsonView(View.HostelResident.class)
    private String name;

    /**
     * Фамилия.
     */
    @Column(name = "surname", unique = false, nullable = true)
    @JsonView(View.HostelResident.class)
    private String surname;

    /**
     * Ебатчество.
     */
    @Column(name = "patronymic", unique = false, nullable = true)
    @JsonView(View.HostelResident.class)
    private String patronymic;

    /**
     * Логин.
     */
    @Column(name = "mail", unique = true, nullable = true)
    @JsonView(View.HostelResident.class)
    private String mail;

    /**
     * Тип комнаты по половой принадлежности.
     */
    @Column(name = "gender", unique = true, nullable = true)
    @JsonView(View.HostelResident.class)
    private PreferredRoomType preferredRoomType;

    /**
     * Поролб.
     */
    @Column(name = "Password", unique = false, nullable = true)
    @JsonView(View.Protected.class)
    private String password;

    /**
     * Роль.
     */
    @Column(name = "role", unique = false, nullable = true)
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
