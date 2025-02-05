package com.nbu.Graduation_System.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDto extends UserDto {
    private String facultyNumber;
    private DepartmentDto department;
}
