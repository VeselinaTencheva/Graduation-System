package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "theses")
public class Thesis extends BaseEntity {
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private LocalDateTime submissionDate;
    
    @OneToOne
    @JoinColumn(name = "thesis_application_id")
    private ThesisApplication thesisApplication;
    
    @OneToOne(mappedBy = "thesis")
    private ThesisReview review;
    
    @OneToOne(mappedBy = "thesis")
    private ThesisDefense defense;
}
