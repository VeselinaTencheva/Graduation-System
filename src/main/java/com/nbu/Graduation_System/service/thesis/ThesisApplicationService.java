package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ThesisApplicationService {
    ThesisApplication save(ThesisApplication application);
    Optional<ThesisApplication> findById(Long id);
    List<ThesisApplication> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Set<ThesisApplication> findByStudent(Student student);
    Set<ThesisApplication> findBySupervisor(Teacher supervisor);
    ThesisApplication updateStatus(Long id, ThesisApplicationStatusType status);
}
