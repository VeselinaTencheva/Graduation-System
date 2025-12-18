package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.DefenseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DefenseSessionRepository extends JpaRepository<DefenseSession, Long> {

    List<DefenseSession> findByDefenseDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
           SELECT s
           FROM DefenseSession s
           JOIN s.committeeMembers cm
           WHERE cm.id = :teacherId
           """)
    List<DefenseSession> findByCommitteeMemberId(@Param("teacherId") Long teacherId);
}
