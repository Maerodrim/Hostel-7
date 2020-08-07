package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.SettlingInDorms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.HashSet;

@Repository
@Table(name = "CheckInQueueRepository")
public interface CheckInQueueRepository extends CrudRepository<CheckInQueue, Long> {
    CheckInQueue findBySettlingInDorms(SettlingInDorms settlingInDorms);
    HashSet<CheckInQueue> findByStatusFalse();
}
