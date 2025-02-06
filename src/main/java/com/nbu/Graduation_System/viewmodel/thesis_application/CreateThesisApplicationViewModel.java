package com.nbu.Graduation_System.viewmodel.thesis_application;

import java.time.LocalDateTime;

import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class CreateThesisApplicationViewModel {
    @NotNull(message = "Submission date cannot be null")
    @PastOrPresent(message = "Submission date cannot be in the future")
    private LocalDateTime submissionDate;

    @NotNull
    private boolean isPositive;

    @NotNull
    private TeacherViewModel reviewer;

    @NotNull
    private ThesisViewModel thesis;
}
