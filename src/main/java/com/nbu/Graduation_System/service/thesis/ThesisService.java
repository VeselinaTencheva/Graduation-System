package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisDto;
import com.nbu.Graduation_System.entity.ThesisApplication;

import java.util.List;
import java.util.Optional;

public interface ThesisService {
    ThesisDto save(ThesisDto thesis);
    Optional<ThesisDto> findById(Long id);
    List<ThesisDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    ThesisDto createFromApplication(ThesisApplication application);
}
