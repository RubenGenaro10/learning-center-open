package com.acme.learningcenterplataform.learning.domain.model.commands;

public record CompleteTutorialForEnrollmentCommand(Long enrollmentId,
                                                   Long tutorialId) {
}
