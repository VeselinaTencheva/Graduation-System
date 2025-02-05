package com.nbu.Graduation_System.entity;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.entity.base.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "departments")
public class Department extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private DepartmentType type;

    private String description;
    private String headOfDepartment;
    private String contactEmail;
    private String phoneNumber;
    private String location;
}
