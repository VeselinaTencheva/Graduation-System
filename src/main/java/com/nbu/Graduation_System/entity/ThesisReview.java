package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "thesis_reviews")
public class ThesisReview extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(nullable = false)
    private LocalDateTime reviewDate;
    
    private boolean isPositive;
    
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Teacher reviewer;
    
    @OneToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
