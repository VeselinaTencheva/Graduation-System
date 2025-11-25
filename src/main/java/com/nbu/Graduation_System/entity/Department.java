package com.nbu.Graduation_System.entity;

import com.nbu.Graduation_System.entity.base.BaseEntity;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "departments")
public class Department extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    private String description;
    private String contactEmail;

    @ManyToOne
    @JoinColumn(name = "dean_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher dean;

    @OneToMany(mappedBy = "department")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Teacher> teachers;
}
