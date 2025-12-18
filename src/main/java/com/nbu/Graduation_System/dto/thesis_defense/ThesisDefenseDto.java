package com.nbu.Graduation_System.dto.thesis_defense;

import lombok.Data;

import com.nbu.Graduation_System.dto.defense_session.DefenseSessionDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(exclude = {"session", "thesis"})
@EqualsAndHashCode(exclude = {"session", "thesis"})
public class ThesisDefenseDto {
    private Long id;

    @Min(2)
    @Max(6)
    private Double grade;

    @NotNull
    private ThesisDto thesis;

    @NotNull
    private DefenseSessionDto session;
}
