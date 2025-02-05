package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
public class Student extends User {
    private String facultyNumber;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToOne(mappedBy = "student")
    private ThesisApplication currentThesisApplication;
}
