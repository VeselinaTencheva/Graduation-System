package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
    Set<ThesisApplication> findByStudent(Student student);
    Set<ThesisApplication> findBySupervisor(Teacher supervisor);
}
