package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.mapper.ThesisDefenseMapper;
import com.nbu.Graduation_System.repository.ThesisDefenseRepository;
import com.nbu.Graduation_System.repository.ThesisRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {
    
    private final ThesisDefenseRepository thesisDefenseRepository;
    private final ThesisRepository thesisRepository;
    private final ThesisDefenseMapper thesisDefenseMapper;

    public ThesisDefenseServiceImpl(ThesisDefenseRepository thesisDefenseRepository,
                                  ThesisRepository thesisRepository,
                                  ThesisDefenseMapper thesisDefenseMapper) {
        this.thesisDefenseRepository = thesisDefenseRepository;
        this.thesisRepository = thesisRepository;
        this.thesisDefenseMapper = thesisDefenseMapper;
    }

    @Override
    public ThesisDefenseDto save(ThesisDefenseDto defenseDto) {
        ThesisDefense defense = thesisDefenseMapper.toEntity(defenseDto);
        defense = thesisDefenseRepository.save(defense);
        return thesisDefenseMapper.toDto(defense);
    }

    @Override
    public Optional<ThesisDefenseDto> findById(Long id) {
        return thesisDefenseRepository.findById(id)
                .map(thesisDefenseMapper::toDto);
    }

    @Override
    public List<ThesisDefenseDto> findAll() {
        return thesisDefenseRepository.findAll().stream()
                .map(thesisDefenseMapper::toDto)
                .collect(Collectors.toList());
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
    public ThesisDefenseDto scheduleDefense(Long thesisId, LocalDateTime defenseDate, Set<Teacher> committee) {
        Thesis thesis = thesisRepository.findById(thesisId)
            .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));

        ThesisDefense defense = new ThesisDefense();
        defense.setThesis(thesis);
        defense.setDefenseDate(defenseDate);
        defense.setCommitteeMembers(committee);
        
        defense = thesisDefenseRepository.save(defense);
        return thesisDefenseMapper.toDto(defense);
    }

    @Override
    public ThesisDefenseDto gradeDefense(Long defenseId, Double grade) {
        ThesisDefense defense = thesisDefenseRepository.findById(defenseId)
            .orElseThrow(() -> new EntityNotFoundException("Defense not found"));
            
        if (grade < 2.0 || grade > 6.0) {
            throw new IllegalArgumentException("Grade must be between 2.0 and 6.0");
        }

        defense.setGrade(grade);
        defense = thesisDefenseRepository.save(defense);
        return thesisDefenseMapper.toDto(defense);
    }
}
