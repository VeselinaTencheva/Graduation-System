package com.nbu.Graduation_System.dto;

import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ThesisApplicationDto {
    private Long id;
    private String title;
    private String objective;
    private String tasks;
    private String technologies;
    private LocalDateTime submissionDate;
    private ThesisApplicationStatusType status;
    private StudentDto student;
    private TeacherDto supervisor;
}
