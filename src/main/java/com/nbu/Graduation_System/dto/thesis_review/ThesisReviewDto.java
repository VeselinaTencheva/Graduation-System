package com.nbu.Graduation_System.dto.thesis_review;

import lombok.Data;
import java.time.LocalDateTime;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;

@Data
public class ThesisReviewDto {
    private Long id;
    private String comments;
    private LocalDateTime reviewDate;
    private boolean isPositive;
    private ThesisDto thesis;
    private TeacherDto reviewer;
}
