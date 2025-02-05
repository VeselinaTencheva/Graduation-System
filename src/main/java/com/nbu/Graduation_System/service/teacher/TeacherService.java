package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.dto.TeacherDto;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    TeacherDto save(TeacherDto teacher);
    Optional<TeacherDto> findById(Long id);
    List<TeacherDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
