package com.nbu.Graduation_System.service.thesis;

import java.util.List;

import com.nbu.Graduation_System.dto.thesis_defense.CreateThesisDefenseDto;
import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;

public interface ThesisDefenseService {
    ThesisDefenseDto save(CreateThesisDefenseDto defense);
    ThesisDefenseDto findById(Long id);
    List<ThesisDefenseDto> findAll();
    List<ThesisDefenseDto> findByTeacherId(Long teacherId);
    void deleteById(Long id);
    // boolean existsById(Long id);
    // ThesisDefenseDto scheduleDefense(Long thesisId, LocalDateTime defenseDate, Set<Teacher> committee);
    // ThesisDefenseDto gradeDefense(Long defenseId, Double grade);
}
