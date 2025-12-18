package com.nbu.Graduation_System.service.defense_session;

import com.nbu.Graduation_System.dto.defense_session.CreateDefenseSessionDto;
import com.nbu.Graduation_System.dto.defense_session.DefenseSessionDto;
import com.nbu.Graduation_System.entity.DefenseSession;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.repository.DefenseSessionRepository;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.repository.ThesisDefenseRepository;
import com.nbu.Graduation_System.repository.ThesisRepository;
import com.nbu.Graduation_System.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefenseSessionServiceImpl implements DefenseSessionService {

    private final DefenseSessionRepository defenseSessionRepository;
    private final TeacherRepository teacherRepository;
    private final ThesisRepository thesisRepository;
    private final ThesisDefenseRepository thesisDefenseRepository;
    private final MapperUtil mapperUtil;

    @Override
    @Transactional
    public DefenseSessionDto createSession(CreateDefenseSessionDto dto) {

        DefenseSession session = new DefenseSession();
        session.setDefenseDate(dto.getDefenseDate());

        List<Teacher> committee = new ArrayList<>(teacherRepository.findAllById(dto.getCommitteeTeacherIds()));
        session.setCommitteeMembers(committee);

        DefenseSession savedSession = defenseSessionRepository.save(session);

        for (Long thesisId : dto.getThesisIds()) {
            Thesis thesis = thesisRepository.findById(thesisId)
                .orElseThrow(() -> new RuntimeException("Thesis not found with id: " + thesisId));

            ThesisDefense defense = new ThesisDefense();
            defense.setSession(savedSession);
            defense.setThesis(thesis);

            thesisDefenseRepository.save(defense);
        }

        savedSession = defenseSessionRepository.findById(savedSession.getId())
            .orElseThrow(() -> new RuntimeException("DefenseSession not found after save"));

        return mapperUtil.getModelMapper().map(savedSession, DefenseSessionDto.class);
    }

    @Override
    public DefenseSessionDto findById(Long id) {
        DefenseSession session = defenseSessionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DefenseSession not found with id: " + id));
        return mapperUtil.getModelMapper().map(session, DefenseSessionDto.class);
    }

    @Override
    public List<DefenseSessionDto> findAll() {
        return mapperUtil.mapList(defenseSessionRepository.findAll(), DefenseSessionDto.class);
    }

    @Override
    public List<DefenseSessionDto> findByCommitteeMember(Long teacherId) {
        return mapperUtil.mapList(
            defenseSessionRepository.findByCommitteeMemberId(teacherId),
            DefenseSessionDto.class
        );
    }
}
