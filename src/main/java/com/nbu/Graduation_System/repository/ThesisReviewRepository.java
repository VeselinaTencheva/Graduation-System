package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisReview;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ThesisReviewRepository extends JpaRepository<ThesisReview, Long> {
    Set<ThesisReview> findByThesis(Thesis thesis);
    Set<ThesisReview> findByReviewer(Teacher reviewer);
}
