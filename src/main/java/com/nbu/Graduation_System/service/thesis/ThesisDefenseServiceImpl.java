package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.repository.ThesisDefenseRepository;
import com.nbu.Graduation_System.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {
    
    private final ThesisDefenseRepository thesisDefenseRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ThesisDefenseDto findById(Long id) {
        ThesisDefense defense = thesisDefenseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ThesisDefense with id=" + id + " not found!"));
        return mapperUtil.getModelMapper().map(defense, ThesisDefenseDto.class);
    }

    @Override
    public List<ThesisDefenseDto> findAll() {
        return mapperUtil.mapList(thesisDefenseRepository.findAll(), ThesisDefenseDto.class);
    }

    @Override
    public List<ThesisDefenseDto> findByCommitteeMemberId(Long teacherId) {
        return mapperUtil.mapList(
            thesisDefenseRepository.findByCommitteeMemberId(teacherId),
            ThesisDefenseDto.class
        );
    }

    @Override
    public List<ThesisDefenseDto> findBySessionId(Long sessionId) {
        return mapperUtil.mapList(
            thesisDefenseRepository.findBySessionId(sessionId),
            ThesisDefenseDto.class
        );
    }

    @Override
    @Transactional
    public ThesisDefenseDto updateGrade(Long defenseId, Double grade) {
        if (grade == null || grade < 2.0 || grade > 6.0) {
            throw new IllegalArgumentException("Grade must be between 2.0 and 6.0");
        }

        ThesisDefense defense = thesisDefenseRepository.findById(defenseId)
            .orElseThrow(() -> new RuntimeException("ThesisDefense not found with id=" + defenseId));

        defense.setGrade(grade);
        defense = thesisDefenseRepository.save(defense);
        return mapperUtil.getModelMapper().map(defense, ThesisDefenseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        thesisDefenseRepository.deleteById(id);
    }
}
