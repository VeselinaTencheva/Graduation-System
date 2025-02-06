package com.nbu.Graduation_System.viewmodel.thesis_review;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ThesisReviewViewModel {

    private Long id;

    @NotNull(message = "Submission date cannot be null")
    @PastOrPresent(message = "Submission date cannot be in the future")
    private LocalDateTime submissionDate;

    @NotNull
    private boolean isPositive;

    @NotNull
    private TeacherDto reviewer;

    @NotNull
    private ThesisDto thesis;
}
