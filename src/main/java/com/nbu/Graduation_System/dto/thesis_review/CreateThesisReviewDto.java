package com.nbu.Graduation_System.dto.thesis_review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateThesisReviewDto {

    @NotNull
    @NotBlank
    private String comments;

    @NotNull
    private boolean isPositive;

    @NotNull
    private TeacherDto reviewer;

    @NotNull
    private ThesisDto thesis;

}
