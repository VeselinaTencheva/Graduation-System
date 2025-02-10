package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_defense.CreateThesisDefenseDto;
import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.repository.ThesisDefenseRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {
    
    private final ThesisDefenseRepository thesisDefenseRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ThesisDefenseDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                thesisDefenseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ThesisDefense with id=" + id + " not found!")),
                ThesisDefenseDto.class);
    }

    @Override
    public List<ThesisDefenseDto> findAll() {
        return mapperUtil.mapList(thesisDefenseRepository.findAll(), ThesisDefenseDto.class);
    }

    @Override
    public List<ThesisDefenseDto> findByTeacherId(Long teacherId) {
        return mapperUtil.mapList(thesisDefenseRepository.findByTeacherId(teacherId), ThesisDefenseDto.class);
    }

    @Override
    public ThesisDefenseDto save(CreateThesisDefenseDto defenseDto) {
        ThesisDefense defense = mapperUtil.getModelMapper().map(defenseDto, ThesisDefense.class);
        defense = thesisDefenseRepository.save(defense);
        return mapperUtil.getModelMapper().map(defense, ThesisDefenseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        thesisDefenseRepository.deleteById(id);
    }

    // @Override
    // public boolean existsById(Long id) {
    //     return thesisDefenseRepository.existsById(id);
    // }

    // @Override
    // public ThesisDefenseDto scheduleDefense(Long thesisId, LocalDateTime defenseDate, Set<Teacher> committee) {
    //     Thesis thesis = thesisRepository.findById(thesisId)
    //         .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));

    //     ThesisDefense defense = new ThesisDefense();
    //     defense.setThesis(thesis);
    //     defense.setDefenseDate(defenseDate);
    //     defense.setCommitteeMembers(committee);
        
    //     defense = thesisDefenseRepository.save(defense);
    //     return thesisDefenseMapper.toDto(defense);
    // }

    // @Override
    // public ThesisDefenseDto gradeDefense(Long defenseId, Double grade) {
    //     ThesisDefense defense = thesisDefenseRepository.findById(defenseId)
    //         .orElseThrow(() -> new EntityNotFoundException("Defense not found"));
            
    //     if (grade < 2.0 || grade > 6.0) {
    //         throw new IllegalArgumentException("Grade must be between 2.0 and 6.0");
    //     }

    //     defense.setGrade(grade);
    //     defense = thesisDefenseRepository.save(defense);
    //     return thesisDefenseMapper.toDto(defense);
    // }
}
