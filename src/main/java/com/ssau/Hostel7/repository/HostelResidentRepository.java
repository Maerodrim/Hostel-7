package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.HostelResident;
import com.ssau.Hostel7.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Optional;
import java.util.UUID;

@Repository
@Table(name = "HostelResident")
public interface HostelResidentRepository extends CrudRepository<HostelResident, UUID> {

    Optional<HostelResident> findByProfile(Profile profile);

}
