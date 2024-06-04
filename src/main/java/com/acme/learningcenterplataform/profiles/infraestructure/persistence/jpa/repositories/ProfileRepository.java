package com.acme.learningcenterplataform.profiles.infraestructure.persistence.jpa.repositories;

import com.acme.learningcenterplataform.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterplataform.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmail(EmailAddress emailAddress);

    @Override
    List<Profile> findAll();


}
