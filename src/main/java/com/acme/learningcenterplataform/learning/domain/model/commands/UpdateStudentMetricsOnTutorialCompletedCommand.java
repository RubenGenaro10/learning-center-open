package com.acme.learningcenterplataform.learning.domain.model.commands;

import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.AcmeStudentRecordId;

public record UpdateStudentMetricsOnTutorialCompletedCommand(
        AcmeStudentRecordId studentRecordId
) {
}
