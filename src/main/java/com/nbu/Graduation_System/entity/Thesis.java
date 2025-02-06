package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "theses")
public class Thesis extends BaseEntity {

    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String title;
    
    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 5, max = 100, message="Min 5, Max 100")
    private String content;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "thesis_application_id", nullable = false)
    private ThesisApplication thesisApplication;
    
    @OneToOne(mappedBy = "thesis")
    private ThesisReview review;
    
    @OneToOne(mappedBy = "thesis")
    private ThesisDefense defense;
}
