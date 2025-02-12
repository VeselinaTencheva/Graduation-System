package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
    List<ThesisApplication> findByStudentId(Long studentId);
    List<ThesisApplication> findBySupervisorId(Long supervisorId);
    boolean existsByStudentAndStatus(Student student, ThesisApplicationStatusType status);
}
