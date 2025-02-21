package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@Repository
public interface ThesisReviewRepository extends JpaRepository<ThesisReview, Long> {
    Optional<ThesisReview> findByThesisId(Long thesisId);
    Set<ThesisReview> findByReviewer(Teacher reviewer);
    List<ThesisReview> findByThesis_ThesisApplication_Student_Id(Long studentId);
}
