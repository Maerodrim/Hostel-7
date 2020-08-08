package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.Status;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Сущность заселяющегося.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "SettlingInDorms")
public class SettlingInDorms {

    /**
     * Идентификатор заселяющегося.
     */
    @Id
    @JsonView(View.CheckInQueue.class)
    private UUID id;

    /**
     * Статус заселяющегося.
     */
    @Column(name = "status", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private Status status;

    /**
     * Ссылка на профиль заселяющегося.
     */
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
