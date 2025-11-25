package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "teachers")
public class Teacher extends User {
    @ManyToOne
    @JoinColumn(name = "department_id")
    @EqualsAndHashCode.Exclude
    private Department department;
    
    private String academicTitle;
    
    @OneToMany(mappedBy = "supervisor")
    @EqualsAndHashCode.Exclude
    private List<ThesisApplication> supervisedTheses;
    
    @OneToMany(mappedBy = "reviewer")
    @EqualsAndHashCode.Exclude
    private List<ThesisReview> reviews;
}

