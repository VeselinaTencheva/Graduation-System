package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "theses")
public class Thesis extends BaseEntity {
    
    @Column(columnDefinition = "TEXT")
    @NotBlank
    private String content;
    
    @Column(nullable = false)
    private LocalDateTime uploadDate;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thesis_application_id")
    private ThesisApplication thesisApplication;
    
    @OneToOne(mappedBy = "thesis", cascade = CascadeType.ALL, orphanRemoval = true)
    private ThesisReview review;
    
    @OneToOne(mappedBy = "thesis", cascade = CascadeType.ALL, orphanRemoval = true)
    private ThesisDefense defense;
    
    // Delegate title to thesis application
    public String getTitle() {
        return thesisApplication != null ? thesisApplication.getTitle() : null;
    }
}
