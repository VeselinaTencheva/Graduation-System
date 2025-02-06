package com.nbu.Graduation_System.dto.thesis_application;

import com.nbu.Graduation_System.viewmodel.student.CreateStudentViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.CreateTeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.CreateThesisViewModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateThesisApplicationDto {
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
    private CreateThesisViewModel thesis;

    @NotNull
    private CreateStudentViewModel student;

    @NotNull
    private CreateTeacherViewModel supervisor;
}
