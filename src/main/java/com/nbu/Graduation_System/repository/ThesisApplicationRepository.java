package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
    List<ThesisApplication> findByStudentId(Long studentId);
    List<ThesisApplication> findBySupervisorId(Long supervisorId);
    boolean existsByStudentAndStatus(Student student, ThesisApplicationStatusType status);
    
    @Query(
        value = """
            SELECT ta.*
            FROM thesis_applications ta
            LEFT JOIN users t ON t.id = ta.supervisor_id
            WHERE t.department_id = :departmentId
            """,
        nativeQuery = true
    )
    List<ThesisApplication> findByDepartmentId(@Param("departmentId") Long departmentId);
}
