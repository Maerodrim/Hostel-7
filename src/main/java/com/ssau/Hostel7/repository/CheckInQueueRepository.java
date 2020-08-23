package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.CheckInQueue;
import com.ssau.Hostel7.model.SettlingInDorms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CheckInQueueRepository extends CrudRepository<CheckInQueue, UUID> {
    CheckInQueue findBySettler(SettlingInDorms settlingInDorms);
    HashSet<CheckInQueue> findByIsSettledIsFalseOrderByTime();

    Optional<CheckInQueue> findFirstByIsSettledIsFalseOrderByTimeDesc();

}
