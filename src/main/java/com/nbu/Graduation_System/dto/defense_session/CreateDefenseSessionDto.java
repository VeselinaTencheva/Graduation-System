package com.nbu.Graduation_System.dto.defense_session;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDefenseSessionDto {

    @NotNull
    private LocalDateTime defenseDate;

    @NotEmpty
    private List<Long> committeeTeacherIds;

    @NotEmpty
    private List<Long> thesisIds;
}
