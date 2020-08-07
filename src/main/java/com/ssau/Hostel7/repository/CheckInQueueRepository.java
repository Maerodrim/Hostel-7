package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.CheckInQueue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "CheckInQueueRepository")
public interface CheckInQueueRepository extends CrudRepository<CheckInQueue, Long> {
}
