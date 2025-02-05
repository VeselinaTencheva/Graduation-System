package com.nbu.Graduation_System.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ThesisDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime submissionDate;
    private ThesisApplicationDto thesisApplication;
}
