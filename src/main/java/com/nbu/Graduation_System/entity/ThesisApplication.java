package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "thesis_applications")
public class ThesisApplication extends BaseEntity {
    private String title;
    private String objective;
    private String tasks;
    private String technologies;
    private LocalDateTime submissionDate;
    
    @Enumerated(EnumType.STRING)
    private ThesisApplicationStatusType status;
    
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Teacher supervisor;
    
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @OneToOne(mappedBy = "thesisApplication")
    private Thesis thesis;
}