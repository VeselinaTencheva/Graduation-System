package com.nbu.Graduation_System.dto.student;

import com.nbu.Graduation_System.dto.department.DepartmentDto;
import com.nbu.Graduation_System.dto.user.CreateUserDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateStudentDto extends CreateUserDto {

    @NotBlank // add validation for a positive number
    private String facultyNumber;

    @NotNull
    private DepartmentDto department;
}
