package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "DormitoryPayment")
public class DormitoryPayment {
    @Id
    @JsonView(View.Hostel.class)
    private UUID idDormitoryPayment;
    @Column(name = "idHostelResident", unique = false, nullable = true)
    @JsonView(View.Room.class)
    private Integer idHostel;
    @Column(name = "End Period", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time endPeriod;
    @Column(name = "Start Period", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time startPeriod;
    @Column(name = "Day", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time day;

    @PrePersist
    public void generateId() {
        this.idDormitoryPayment = UUID.randomUUID();
    }
}
