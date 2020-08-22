package com.ssau.Hostel7.model;

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
 * Сущность заселённого.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "HostelResident")
public class HostelResident {

    /**
     * Идентификатор заселённого.
     */
    @Id
    private UUID id;

    /**
     * Номер студенческого билета.
     */
    @Column(name = "Student_ID_Number", unique = true, nullable = true)
    private String studentIDNumber;

    /**
     * Номер договора.
     */
    @Column(name = "contract_id_number", unique = true)
    private String contractIdNumber;

    /**
     * Номер группы.
     */
    @Column(name = "Group_number", unique = false, nullable = true)
    private String groupNumber;

    /**
     * Номер пропуска.
     */
    @Column(name = "pass_number")
    private String passNumber;

    /**
     * Профиль заселённого.
     */
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
