package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Optional<Student> findByFacultyNumber(String facultyNumber);
    
    @Query("SELECT s FROM Student s WHERE NOT EXISTS " +
           "(SELECT 1 FROM ThesisApplication ta WHERE ta.student = s AND " +
           "(ta.status = 'ACCEPTED' OR ta.status = 'SUBMITTED'))")
    List<Student> findAllEligibleForThesisApplication();
}
