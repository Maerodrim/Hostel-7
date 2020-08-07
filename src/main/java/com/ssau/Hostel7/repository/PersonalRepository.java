package com.ssau.Hostel7.repository;

import com.ssau.Hostel7.model.Personal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "Personal")
public interface PersonalRepository extends CrudRepository<Personal, Long> {
}
