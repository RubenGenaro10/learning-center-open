package com.acme.learningcenterplataform.learning.domain.model.entities;

import com.acme.learningcenterplataform.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterplataform.learning.domain.model.valuesobjects.ProgressStatus;
import com.acme.learningcenterplataform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
public class ProgressRecordItem  extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Getter
    private Long tutorialId;

    private ProgressStatus status;

    private Date startedAt;

    private Date completedAt;

    public ProgressRecordItem() {

    }

    public ProgressRecordItem(Enrollment enrollment, Long tutorialId) {
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complet() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isCompleted() {
        return this.status == ProgressStatus.COMPLETED;
    }

    public boolean isInProgress() {
        return this.status == ProgressStatus.STARTED;
    }

    public boolean isNotStarted() {
        return this.status == ProgressStatus.NOT_STARTED;
    }

    public long calculateDaysElapsed() {
        if (this.status == ProgressStatus.NOT_STARTED) return 0;
        var defaultTimeZone = java.time.ZoneId.systemDefault();
        var fromDate = this.startedAt.toInstant();
        var toDate = this.completedAt == null ? LocalDate.now().
                atStartOfDay(defaultTimeZone).toInstant() : this.completedAt.toInstant();
        return java.time.Duration.between(fromDate,toDate).toDays();
    }
}
