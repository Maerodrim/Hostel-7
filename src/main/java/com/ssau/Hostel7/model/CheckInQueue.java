package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssau.Hostel7.view.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "CheckInQueue")
public class CheckInQueue {
    @Id
    @JsonView(View.HostelResident.class)
    private UUID idCheckInQueue;
    @OneToOne
    @Column(name = "Settling In Dorms", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private SettlingInDorms settlingInDorms;
    @Column(name = "Time", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Time time;
    @Column(name = "Check-in status", unique = false, nullable = true)
    @JsonView(View.Hostel.class)
    private Boolean status;

    @PrePersist
    public void generateId() {
        this.idCheckInQueue = UUID.randomUUID();
    }
}
