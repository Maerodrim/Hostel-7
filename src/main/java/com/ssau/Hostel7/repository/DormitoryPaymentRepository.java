package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.DormitoryPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "DormitoryPaymentRepository")
public interface DormitoryPaymentRepository extends CrudRepository<DormitoryPayment, Long> {
}
