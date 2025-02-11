package com.nbu.Graduation_System.dto.thesis_application;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.department.DepartmentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateThesisApplicationDto {
    
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
    private String title;

    @NotBlank(message = "Objective is required")
    @Size(min = 20, max = 300, message = "Objective must be between 20 and 300 characters")
    private String objective;

    @NotBlank(message = "Tasks are required")
    @Size(min = 20, max = 300, message = "Tasks must be between 20 and 300 characters")
    private String tasks;

    @NotBlank(message = "Technologies are required")
    @Size(min = 20, max = 300, message = "Technologies must be between 20 and 300 characters")
    private String technologies;

    @NotNull(message = "Student is required")
    private Long studentId;

    @NotNull(message = "Supervisor is required")
    private TeacherDto supervisor;

    @NotNull(message = "Department is required")
    private DepartmentDto department;
}
