package com.nbu.Graduation_System.dto.student;

import com.nbu.Graduation_System.dto.department.DepartmentDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;

import com.nbu.Graduation_System.dto.user.UserDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDto extends UserDto {

    @NotBlank // add validation for a positive number
    private String facultyNumber;

    @NotNull
    private DepartmentDto department;

    private ThesisApplicationDto thesisApplication;
}
