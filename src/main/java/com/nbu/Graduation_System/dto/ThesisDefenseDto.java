package com.nbu.Graduation_System.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ThesisDefenseDto {
    private Long id;
    private LocalDateTime defenseDate;
    private Double grade;
    private ThesisDto thesis;
    private Set<TeacherDto> committeeMembers;
}
