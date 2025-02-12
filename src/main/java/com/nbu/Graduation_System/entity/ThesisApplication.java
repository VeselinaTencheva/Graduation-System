package com.nbu.Graduation_System.entity;

import com.nbu.Graduation_System.entity.base.BaseEntity;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "thesis_applications")
public class ThesisApplication extends BaseEntity {

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String objective;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String tasks;
    
    @Column(nullable = false)
    private String technologies;
    
    @Column(nullable = false)
    private LocalDateTime submissionDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ThesisApplicationStatusType status = ThesisApplicationStatusType.SUBMITTED;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    private Teacher supervisor;

    @OneToOne(mappedBy = "thesisApplication")
    private Thesis thesis;

    public boolean isApproved() {
        return status == ThesisApplicationStatusType.ACCEPTED;
    }

    public boolean isRejected() {
        return status == ThesisApplicationStatusType.REJECTED;
    }
}