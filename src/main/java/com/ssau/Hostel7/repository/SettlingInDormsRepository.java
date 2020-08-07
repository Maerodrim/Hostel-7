package com.ssau.Hostel7.repository;


import com.ssau.Hostel7.model.SettlingInDorms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.UUID;

@Repository
@Table(name = "SettlingInDorms")
public interface SettlingInDormsRepository extends CrudRepository<SettlingInDorms, Long> {
    SettlingInDorms findByIdSettlingInDorms(UUID idSettlingInDorms);
}
