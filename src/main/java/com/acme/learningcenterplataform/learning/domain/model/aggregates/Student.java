package com.acme.learningcenterplataform.learning.domain.model.aggregates;

import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.AcmeStudentRecordId;
import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.ProfileId;
import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.StudentPerformanceMetricsSet;
import com.acme.learningcenterplataform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {

    @Getter
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetricsSet performanceMetricSet;

    public Student() {
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StudentPerformanceMetricsSet();
    }

    public Student(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public Student(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    public void updateMetricsOnCourseCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalCompletedCourses();
    }

    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalTutorials();
    }

    public String getStudentRecordId() { return this.acmeStudentRecordId.studentRecordId(); }

    public  Long getProfileId() { return this.profileId.profileId(); }

    public Integer getTotalCompletedCourses() { return performanceMetricSet.totalCompletedCourses(); }
    public Integer getTotalTutorials() { return performanceMetricSet.totalTutorials(); }
}
