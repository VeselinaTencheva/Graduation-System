package com.nbu.Graduation_System.viewmodel.defense_session;

import java.time.LocalDateTime;
import java.util.List;

import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.thesis_defense.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefenseSessionViewModel {
    private Long id;
    private LocalDateTime defenseDate;
    private List<TeacherViewModel> committeeMembers;
    private List<ThesisDefenseViewModel> defenses;
}
