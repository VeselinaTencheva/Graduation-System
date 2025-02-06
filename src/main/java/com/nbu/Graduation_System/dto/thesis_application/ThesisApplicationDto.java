package com.nbu.Graduation_System.dto.thesis_application;


import com.nbu.Graduation_System.dto.student.StudentDto;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ThesisApplicationDto {
    
    @NotNull
    private Long id;

    @NotBlank
    // @Size(min = 5, max = 50, message="Min 5, Max 50")
    private String title;

    @NotBlank
    // @Size(min = 20, max = 300, message="Min 50, Max 300")
    private String objective;

    @NotBlank
    // @Size(min = 20, max = 300, message="Min 50, Max 300")
    private String tasks;

    @NotBlank
    // @Size(min = 20, max = 300, message="Min 50, Max 300")
    private String technologies;

    @NotNull
    private ThesisDto thesis;

    @NotNull
    private StudentDto student;

    @NotNull
    private TeacherDto supervisor;
}
