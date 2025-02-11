package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToMany(mappedBy = "student")
    private Set<ThesisApplication> thesisApplications;

    public String getFacultyNumber() {
        return "F" + getId();
    }
}
