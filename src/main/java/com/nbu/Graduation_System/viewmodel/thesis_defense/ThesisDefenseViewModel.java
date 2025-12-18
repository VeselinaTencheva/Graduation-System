package com.nbu.Graduation_System.viewmodel.thesis_defense;

import com.nbu.Graduation_System.viewmodel.defense_session.DefenseSessionViewModel;
import com.nbu.Graduation_System.viewmodel.thesis.ThesisViewModel;

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
    private Double grade;
    private ThesisViewModel thesis;
    private DefenseSessionViewModel session;
}
