package com.nbu.Graduation_System.dto.thesis_defense;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Data
public class ThesisDefenseDto {
    private Long id;

    @NotNull
    @FutureOrPresent
    private LocalDateTime defenseDate;

    @NotNull
    @Min(2)
    @Max(6)
    private Double grade;

    @NotNull
    private ThesisDto thesis;

    @NotNull
    @Size(min = 2)
    private Set<TeacherDto> committeeMembers;
}
