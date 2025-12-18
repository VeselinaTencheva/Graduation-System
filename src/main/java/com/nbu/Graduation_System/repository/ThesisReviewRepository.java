package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ThesisReviewRepository extends JpaRepository<ThesisReview, Long> {
    Optional<ThesisReview> findByThesisId(Long thesisId);
    List<ThesisReview> findByReviewer(Teacher reviewer);
    List<ThesisReview> findByThesisThesisApplicationStudentId(Long studentId);
    List<ThesisReview> findByReviewerDepartmentId(Long departmentId);
}