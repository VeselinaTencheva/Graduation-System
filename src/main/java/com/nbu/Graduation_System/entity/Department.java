package com.nbu.Graduation_System.entity;

import com.nbu.Graduation_System.entity.base.BaseEntity;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "departments")
public class Department extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @ManyToOne
    @JoinColumn(name = "dean_id")
    private Teacher dean;

    @OneToMany(mappedBy = "department")
    private List<Teacher> teachers;

    private String description;
    private String contactEmail;
}
