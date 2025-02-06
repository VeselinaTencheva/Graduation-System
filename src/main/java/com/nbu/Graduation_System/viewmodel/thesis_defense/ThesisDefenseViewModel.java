package com.nbu.Graduation_System.viewmodel.thesis_defense;

import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

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
public class ThesisDefenseViewModel {

    private Long id;

    @NotNull
    @FutureOrPresent
    private LocalDateTime defenseDate;

    @NotNull
    @Min(2)
    @Max(6)
    private Double grade;

    @NotNull
    private ThesisViewModel thesis;

    @NotNull
    @Size(min = 2)
    private Set<TeacherViewModel> committeeMembers;
}
