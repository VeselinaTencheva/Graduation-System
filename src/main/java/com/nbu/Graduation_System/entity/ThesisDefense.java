package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.Set;

import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "thesis_defenses")
public class ThesisDefense extends BaseEntity {
    private LocalDateTime defenseDate;
    private Double grade;
    
    @OneToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
    
    @ManyToMany
    @JoinTable(
        name = "defense_committee",
        joinColumns = @JoinColumn(name = "defense_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> committeeMembers;
}
