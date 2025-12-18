package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisDefenseRepository extends JpaRepository<ThesisDefense, Long> {

    @Query("""
       SELECT td
       FROM ThesisDefense td
       JOIN td.session s
       JOIN s.committeeMembers cm
       WHERE cm.id = :teacherId
       """)
    List<ThesisDefense> findByCommitteeMemberId(@Param("teacherId") Long teacherId);

    List<ThesisDefense> findBySessionId(Long sessionId);

    List<ThesisDefense> findByThesisId(Long thesisId);
}
