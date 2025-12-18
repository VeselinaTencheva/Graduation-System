package com.nbu.Graduation_System.viewmodel.defense_session;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDefenseSessionViewModel {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime defenseDate;

    @NotEmpty
    private List<Long> committeeTeacherIds;

    @NotEmpty
    private List<Long> thesisIds;
}
