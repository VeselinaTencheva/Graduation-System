package com.nbu.Graduation_System.dto.thesis_review;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateThesisReviewDto {
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
