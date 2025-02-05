package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.entity.Teacher;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ThesisDefenseService {
    ThesisDefense save(ThesisDefense defense);
    Optional<ThesisDefense> findById(Long id);
    List<ThesisDefense> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    ThesisDefense scheduleDefense(Long thesisId, LocalDateTime defenseDate, Set<Teacher> committee);
    ThesisDefense gradeDefense(Long defenseId, Double grade);
}
