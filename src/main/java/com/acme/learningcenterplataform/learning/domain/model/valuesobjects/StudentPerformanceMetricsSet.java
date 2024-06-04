package com.acme.learningcenterplataform.learning.domain.model.valuesobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StudentPerformanceMetricsSet(Integer totalCompletedCourses,
                                           Integer totalTutorials) {
    public StudentPerformanceMetricsSet() { this(0,0);}
    public StudentPerformanceMetricsSet {
        if (totalCompletedCourses < 0) {
            throw new IllegalArgumentException("Total completed courses cannot be negative");
        }
        if (totalTutorials < 0) {
            throw new IllegalArgumentException("Total tutorials courses cannot be negative");
        }
    }

    public StudentPerformanceMetricsSet incrementTotalCompletedCourses() {
        return new StudentPerformanceMetricsSet( totalCompletedCourses + 1,
                                                totalTutorials);
    }
    public StudentPerformanceMetricsSet incrementTotalTutorials() {
        return new StudentPerformanceMetricsSet( totalCompletedCourses,
                totalTutorials + 1);
    }
}
