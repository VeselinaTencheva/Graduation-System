package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.ThesisApplication;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeacherService {
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(Long id);
    List<Teacher> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Set<ThesisApplication> findSupervisedTheses(Long teacherId);
}
