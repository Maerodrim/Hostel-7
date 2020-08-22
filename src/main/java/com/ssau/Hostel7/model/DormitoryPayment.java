package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.model.enumModel.ConfirmationStatus;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

/**
 * Сущность факта оплаты за общежитие.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "DormitoryPayment")
public class DormitoryPayment {
    @Id
    @JsonView(View.Hostel.class)
    private UUID id;
    @Column(name = "idHostelResident", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private Integer idHostel;
    @Column(name = "End_Period", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Date endPeriod;
    @Column(name = "Start_Period", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Date startPeriod;
    @Column(name = "Day", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Date day;
    @Column(name = "Сonfirmation", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private ConfirmationStatus status;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
