package com.nbu.Graduation_System.dto.thesis_application;


import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Data
public class ThesisApplicationDto {
    
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50, message="Min 5, Max 50")
    private String title;

    @NotBlank
    @Size(min = 20, max = 300, message="Min 50, Max 300")
    private String objective;

    @NotBlank
    @Size(min = 20, max = 300, message="Min 50, Max 300")
    private String tasks;

    @NotBlank
    @Size(min = 20, max = 300, message="Min 50, Max 300")
    private String technologies;

    @NotNull
    private ThesisViewModel thesis;

    @NotNull
    private StudentViewModel student;

    @NotNull
    private TeacherViewModel supervisor;
}
