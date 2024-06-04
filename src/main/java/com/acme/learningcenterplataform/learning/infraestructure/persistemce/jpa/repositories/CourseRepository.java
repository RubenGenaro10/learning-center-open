package com.acme.learningcenterplataform.learning.infraestructure.persistemce.jpa.repositories;

import com.acme.learningcenterplataform.learning.domain.model.aggregates.Course;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);

    boolean existsByTitle(String title);

    boolean existsByTitleAndIdIsNot(String title, Long id);
}
