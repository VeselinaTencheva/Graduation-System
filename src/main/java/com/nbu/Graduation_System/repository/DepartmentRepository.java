package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.Department;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByType(DepartmentType type);
}
