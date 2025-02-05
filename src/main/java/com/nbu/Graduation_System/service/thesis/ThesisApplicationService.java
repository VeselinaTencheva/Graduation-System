package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisApplicationDto;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import java.util.List;
import java.util.Set;
import java.util.Optional;

public interface ThesisApplicationService {
    ThesisApplicationDto save(ThesisApplicationDto application);
    Optional<ThesisApplicationDto> findById(Long id);
    List<ThesisApplicationDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Set<ThesisApplicationDto> findByStudentId(Long studentId);
    Set<ThesisApplicationDto> findBySupervisorId(Long supervisorId);
    ThesisApplicationDto updateStatus(Long applicationId, ThesisApplicationStatusType status);
}
