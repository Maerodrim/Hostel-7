package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.HostelResident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "HostelResident")
public interface HostelResidentRepository extends CrudRepository<HostelResident, Long> {
}
