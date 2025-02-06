package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis.CreateThesisDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;
import com.nbu.Graduation_System.entity.ThesisApplication;

import java.util.List;

public interface ThesisService {
    ThesisDto save(CreateThesisDto thesis);
    ThesisDto findById(Long id);
    List<ThesisDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    ThesisDto createFromApplication(ThesisApplication application);
}
