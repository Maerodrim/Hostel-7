package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.Hostel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.UUID;

@Repository
@Table(name = "Hostel")
public interface HostelRepository extends CrudRepository<Hostel, UUID> {
    Hostel findByNumber(Integer number);
}
