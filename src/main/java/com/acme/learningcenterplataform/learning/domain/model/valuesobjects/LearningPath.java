package com.acme.learningcenterplataform.learning.domain.model.valuesobjects;

import com.acme.learningcenterplataform.learning.domain.model.aggregates.Course;
import com.acme.learningcenterplataform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    public LearningPath() {
        this.learningPathItems = new ArrayList<>();
    }

    public void addItem(Course course, Long tutorialId, LearningPathItem nextItem) {
        //Add the new item before the next item
        System.out.println("Adding item to learning path");
        LearningPathItem newLearningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        System.out.println("tutorial Id: " + newLearningPathItem.getTutorialId());
        learningPathItems.add(newLearningPathItem);
    }

    public void addItem(Course course, Long tutorialId) {
        System.out.println("Adding item to learning path");
        LearningPathItem newLearningPathItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!learningPathItems.isEmpty()) {
            originalLastItem = getLastItemInLearningPath();
        }
        else {
            System.out.println("Learning path is empty");
        }
        learningPathItems.add(newLearningPathItem);
        System.out.println("tutorial Id " + newLearningPathItem.getTutorialId());
        System.out.println("Learning path item added");
        if (originalLastItem != null) originalLastItem.updateNextItem(newLearningPathItem);
    }

    public LearningPathItem getLastItemInLearningPath() {
        return  learningPathItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }



    public void addItem(Course course, Long tutorialId, Long nexTutorialId) {
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(nexTutorialId);
        addItem(course, tutorialId, nextItem);
    }

    public LearningPathItem getLearningPathItemWithTutorialId(Long tutorialId) {
        return learningPathItems.stream().filter(learningPathItem ->
                learningPathItem.getTutorialId().equals(tutorialId)).findFirst().orElse(null);
    }

    public Long getFirstTutorialIdInLearningPath() {
        return learningPathItems.get(0).getTutorialId();
    }

    public Long getNextTutorialInLearningParh(Long currentTutorialId) {
        LearningPathItem item = getLearningPathItemWithTutorialId(currentTutorialId);
        return item != null ? item.getTutorialId() : null;
    }

    public boolean isLastTutorialInLearningPath(Long currentTutorialId) {
        return getNextTutorialInLearningParh(currentTutorialId) == null;
    }

    public boolean isEmpty() {
        return learningPathItems.isEmpty();
    }
}
