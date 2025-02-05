package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.Teacher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.Optional;

public interface ThesisDefenseService {
    ThesisDefenseDto save(ThesisDefenseDto defense);
    Optional<ThesisDefenseDto> findById(Long id);
    List<ThesisDefenseDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    ThesisDefenseDto scheduleDefense(Long thesisId, LocalDateTime defenseDate, Set<Teacher> committee);
    ThesisDefenseDto gradeDefense(Long defenseId, Double grade);
}
