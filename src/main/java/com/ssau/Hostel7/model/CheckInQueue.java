package com.ssau.Hostel7.model;

import com.fasterxml.jackson.annotation.JsonView;
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
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "CheckInQueue")
public class CheckInQueue {
    @Id
    private UUID id;

    @JoinColumn(name = "Settling_In_Dorms", unique = false, nullable = true)
    @OneToOne
    private SettlingInDorms settler;

    @Column(name = "Time", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private Date time;

    /**
     * true - заселён.
     */
    @Column(name = "Check_in_status", unique = false, nullable = true)
    @JsonView(View.CheckInQueue.class)
    private Boolean isSettled;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
