package com.nbu.Graduation_System.service.defense_session;

import com.nbu.Graduation_System.dto.defense_session.CreateDefenseSessionDto;
import com.nbu.Graduation_System.dto.defense_session.DefenseSessionDto;

import java.util.List;

public interface DefenseSessionService {
    DefenseSessionDto createSession(CreateDefenseSessionDto dto);
    DefenseSessionDto findById(Long id);
    List<DefenseSessionDto> findAll();
    List<DefenseSessionDto> findByCommitteeMember(Long teacherId);
}
