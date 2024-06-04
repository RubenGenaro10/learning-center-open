package com.acme.learningcenterplataform.learning.infraestructure.persistemce.jpa.repositories;

import com.acme.learningcenterplataform.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId acmeStudentRecordId);
    List<Enrollment> findAllByCourseId(Long courseId);

    Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId acmeStudentRecordId,
                                                              Long courseId);
}
