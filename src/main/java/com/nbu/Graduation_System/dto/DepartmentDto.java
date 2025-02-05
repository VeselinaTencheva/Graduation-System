package com.nbu.Graduation_System.dto;

import com.nbu.Graduation_System.entity.enums.DepartmentType;
import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;
    private DepartmentType type;
    private String description;
    private String contactEmail;
}
