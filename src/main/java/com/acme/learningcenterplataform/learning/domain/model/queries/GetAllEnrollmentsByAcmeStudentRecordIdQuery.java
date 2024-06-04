package com.acme.learningcenterplataform.learning.domain.model.queries;

import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.AcmeStudentRecordId;

public record GetAllEnrollmentsByAcmeStudentRecordIdQuery(
        AcmeStudentRecordId studentRecordId) {
}
