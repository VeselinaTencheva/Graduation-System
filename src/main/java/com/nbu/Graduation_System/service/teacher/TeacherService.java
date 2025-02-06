package com.nbu.Graduation_System.service.teacher;

import java.util.List;
import com.nbu.Graduation_System.dto.teacher.*;

public interface TeacherService {
    TeacherDto save(CreateTeacherDto teacher);
    TeacherDto findById(Long id);
    List<TeacherDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
