package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.repository.ThesisDefenseRepository;
import com.nbu.Graduation_System.repository.ThesisRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {
    
    private final ThesisDefenseRepository thesisDefenseRepository;
    private final ThesisRepository thesisRepository;

    public ThesisDefenseServiceImpl(ThesisDefenseRepository thesisDefenseRepository,
                                  ThesisRepository thesisRepository) {
        this.thesisDefenseRepository = thesisDefenseRepository;
        this.thesisRepository = thesisRepository;
    }

    @Override
    public ThesisDefense save(ThesisDefense defense) {
        return thesisDefenseRepository.save(defense);
    }

    @Override
    public Optional<ThesisDefense> findById(Long id) {
        return thesisDefenseRepository.findById(id);
    }

    @Override
    public List<ThesisDefense> findAll() {
        return thesisDefenseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        thesisDefenseRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return thesisDefenseRepository.existsById(id);
    }

    @Override
    public ThesisDefense scheduleDefense(Long thesisId, LocalDateTime defenseDate, Set<Teacher> committee) {
        Thesis thesis = thesisRepository.findById(thesisId)
            .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));

        ThesisDefense defense = new ThesisDefense();
        defense.setThesis(thesis);
        defense.setDefenseDate(defenseDate);
        defense.setCommitteeMembers(committee);
        
        return thesisDefenseRepository.save(defense);
    }

    @Override
    public ThesisDefense gradeDefense(Long defenseId, Double grade) {
        ThesisDefense defense = thesisDefenseRepository.findById(defenseId)
            .orElseThrow(() -> new EntityNotFoundException("Defense not found"));
            
        if (grade < 2.0 || grade > 6.0) {
            throw new IllegalArgumentException("Grade must be between 2.0 and 6.0");
        }

        defense.setGrade(grade);
        return thesisDefenseRepository.save(defense);
    }
}
