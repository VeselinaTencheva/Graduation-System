package com.nbu.Graduation_System.dto.teacher;

import com.nbu.Graduation_System.dto.department.DepartmentDto;
import com.nbu.Graduation_System.dto.user.UserDto;

import lombok.EqualsAndHashCode;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherDto extends UserDto {

    @NotBlank
    private String academicTitle;

    @NotNull
    private DepartmentDto department;    
}
