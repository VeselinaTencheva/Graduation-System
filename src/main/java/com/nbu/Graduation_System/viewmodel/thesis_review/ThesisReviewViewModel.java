package com.nbu.Graduation_System.viewmodel.thesis_review;

import java.time.LocalDateTime;

import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotNull
    private Long id;

    @NotBlank(message = "Comments are required")
    private String comments;

    private LocalDateTime reviewDate;

    @NotNull(message = "Review type is required")
    private boolean isPositive;

    private ThesisViewModel thesis;

    private TeacherViewModel reviewer;
}
