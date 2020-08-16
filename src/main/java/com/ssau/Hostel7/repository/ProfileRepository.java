package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, UUID> {

    Optional<Profile> findByMail(String mail);

    boolean existsByMail(String mail);

}
