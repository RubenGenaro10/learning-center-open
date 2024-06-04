package com.acme.learningcenterplataform.learning.domain.model.valuesobjects;

import com.acme.learningcenterplataform.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterplataform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId) {
        return progressRecordItems.stream().filter(
                progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream().filter(
                progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }

    public void startTutorial(Long tutorialId) {
        if (hasAnItemInProgress()) throw new IllegalArgumentException("A tutorial is already in progress");
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalArgumentException("Tutorial with given Id is already started or completed");
        }
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress status");
    }

    public void completeTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complet();
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");

        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;

        Long nextTutorialId = learningPath.getNextTutorialInLearningParh(tutorialId);
        if (nextTutorialId != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(),
                    nextTutorialId);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }

    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        if (learningPath.isEmpty()) return;
        Long tutorialId = learningPath.getFirstTutorialIdInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        progressRecordItems.add(progressRecordItem);
    }
}
