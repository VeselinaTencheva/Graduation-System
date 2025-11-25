package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByDepartmentId(Long deparementId);
}
