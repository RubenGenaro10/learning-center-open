package com.acme.learningcenterplataform.learning.infraestructure.persistemce.jpa.repositories;

import com.acme.learningcenterplataform.learning.domain.model.aggregates.Student;
import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.AcmeStudentRecordId;
import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student>  findByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
    Optional<Student> findByProfileId(ProfileId profileId);
}
