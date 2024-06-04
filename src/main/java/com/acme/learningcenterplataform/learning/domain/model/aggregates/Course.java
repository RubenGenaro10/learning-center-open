package com.acme.learningcenterplataform.learning.domain.model.aggregates;

import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.LearningPath;
import com.acme.learningcenterplataform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;

    @Embedded
    private final LearningPath learningPath;

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }

    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    public void addTutorialToLearningPath(Long tutorialId) {
        this.learningPath.addItem(this, tutorialId);
    }

    public void addTutorialToLearnongPath(Long tutorialId, Long nextTutorialId) {
        this.learningPath.addItem(this, tutorialId, nextTutorialId);
    }
}
