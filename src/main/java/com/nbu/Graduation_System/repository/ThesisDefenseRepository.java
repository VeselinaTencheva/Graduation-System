package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.ThesisDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDefenseRepository extends JpaRepository<ThesisDefense, Long> {
}
