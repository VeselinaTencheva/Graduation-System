package com.nbu.Graduation_System.viewmodel.thesis_application;

import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ThesisApplicationViewModel {

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
    private LocalDateTime submissionDate; // get current date, do not enter from the ui
    
    private ThesisApplicationStatusType status; // default value - PENDING, do not set from the ui

    private ThesisViewModel thesis;

    @NotNull
    private StudentViewModel student;

    @NotNull
    private TeacherViewModel supervisor;
}
