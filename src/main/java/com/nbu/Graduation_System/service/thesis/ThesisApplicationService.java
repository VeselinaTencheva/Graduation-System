package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_application.CreateThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;

import java.util.List;

public interface ThesisApplicationService {
    ThesisApplicationDto create(CreateThesisApplicationDto application);
    ThesisApplicationDto save(CreateThesisApplicationDto application);
    ThesisApplicationDto findById(Long id);
    List<ThesisApplicationDto> findBySupervisorId(Long id);
    List<ThesisApplicationDto> findByStudentId(Long id);
    List<ThesisApplicationDto> findAll();
    void deleteById(Long id);
    void approve(Long id);
    void reject(Long id);
    ThesisApplicationDto approveWithStudentId(Long studentId);
    ThesisApplicationDto rejectWithStudentId(Long studentId);
    // boolean existsById(Long id);
    // Set<ThesisApplicationDto> findByStudentId(Long studentId);
    // Set<ThesisApplicationDto> findBySupervisorId(Long supervisorId);
    // ThesisApplicationDto updateStatus(Long applicationId, ThesisApplicationStatusType status);
}
