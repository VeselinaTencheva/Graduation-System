package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisDefenseRepository extends JpaRepository<ThesisDefense, Long> {
    @Query("SELECT td FROM ThesisDefense td JOIN td.committeeMembers cm WHERE cm.id = :teacherId")
    List<ThesisDefense> findByTeacherId(@Param("teacherId") Long teacherId);
}
