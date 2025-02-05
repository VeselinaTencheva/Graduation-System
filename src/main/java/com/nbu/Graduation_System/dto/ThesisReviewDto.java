package com.nbu.Graduation_System.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ThesisReviewDto {
    private Long id;
    private String content;
    private Double grade;
    private LocalDateTime submissionDate;
    private ThesisDto thesis;
    private TeacherDto reviewer;
}
