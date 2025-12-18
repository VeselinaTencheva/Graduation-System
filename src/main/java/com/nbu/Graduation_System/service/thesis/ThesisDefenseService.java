package com.nbu.Graduation_System.service.thesis;

import java.util.List;

import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;

public interface ThesisDefenseService {
    ThesisDefenseDto findById(Long id);
    List<ThesisDefenseDto> findAll();
    List<ThesisDefenseDto> findByCommitteeMemberId(Long teacherId);
    List<ThesisDefenseDto> findBySessionId(Long sessionId);
    ThesisDefenseDto updateGrade(Long defenseId, Double grade);
    void deleteById(Long id);
}
