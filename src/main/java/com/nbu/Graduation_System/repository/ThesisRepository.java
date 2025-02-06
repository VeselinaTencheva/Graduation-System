package com.nbu.Graduation_System.repository;

import com.nbu.Graduation_System.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    @Query("SELECT t FROM Thesis t " +
           "LEFT JOIN FETCH t.thesisApplication ta " +
           "LEFT JOIN FETCH ta.student " +
           "LEFT JOIN FETCH ta.supervisor " +
           "WHERE t.id = :id")
    Optional<Thesis> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT DISTINCT t FROM Thesis t " +
           "LEFT JOIN FETCH t.thesisApplication ta " +
           "LEFT JOIN FETCH ta.student " +
           "LEFT JOIN FETCH ta.supervisor")
    List<Thesis> findAllWithDetails();
}
