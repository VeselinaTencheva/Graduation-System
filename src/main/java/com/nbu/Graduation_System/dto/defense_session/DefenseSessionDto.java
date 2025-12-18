package com.nbu.Graduation_System.dto.defense_session;

import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"defenses"})
@EqualsAndHashCode(exclude = {"defenses"})
public class DefenseSessionDto {
    private Long id;
    private LocalDateTime defenseDate;
    private List<TeacherDto> committeeMembers;
    private List<ThesisDefenseDto> defenses;
}
