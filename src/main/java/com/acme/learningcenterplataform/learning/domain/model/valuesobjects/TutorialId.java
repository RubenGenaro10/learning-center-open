package com.acme.learningcenterplataform.learning.domain.model.valuesobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TutorialId(Long tutorialId) {
    public TutorialId {
        if (tutorialId < 0) {
            throw new IllegalArgumentException("Tutorial tutorialId cannot be negative");
        }
    }
    public TutorialId() {
        this(0L);
    }
}
