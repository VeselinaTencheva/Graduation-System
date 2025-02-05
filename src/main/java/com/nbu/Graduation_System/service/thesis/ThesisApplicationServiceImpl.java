package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisApplicationDto;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.mapper.ThesisApplicationMapper;
import com.nbu.Graduation_System.repository.ThesisApplicationRepository;
import com.nbu.Graduation_System.repository.StudentRepository;
import com.nbu.Graduation_System.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {
    
    private final ThesisApplicationRepository thesisApplicationRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ThesisApplicationMapper thesisApplicationMapper;

    public ThesisApplicationServiceImpl(ThesisApplicationRepository thesisApplicationRepository,
                                      ThesisApplicationMapper thesisApplicationMapper,
                                      StudentRepository studentRepository,
                                      TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.thesisApplicationRepository = thesisApplicationRepository;
        this.thesisApplicationMapper = thesisApplicationMapper;
    }

    @Override
    public ThesisApplicationDto save(ThesisApplicationDto applicationDto) {
        ThesisApplication application = thesisApplicationMapper.toEntity(applicationDto);
        application = thesisApplicationRepository.save(application);
        return thesisApplicationMapper.toDto(application);
    }

    @Override
    public Optional<ThesisApplicationDto> findById(Long id) {
        return thesisApplicationRepository.findById(id)
                .map(thesisApplicationMapper::toDto);
    }

    @Override
    public List<ThesisApplicationDto> findAll() {
        return thesisApplicationRepository.findAll().stream()
                .map(thesisApplicationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        thesisApplicationRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return thesisApplicationRepository.existsById(id);
    }

    @Override
    public Set<ThesisApplicationDto> findByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        return thesisApplicationRepository.findByStudent(student).stream()
                .map(thesisApplicationMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ThesisApplicationDto> findBySupervisorId(Long supervisorId) {
        Teacher supervisor = teacherRepository.findById(supervisorId)
            .orElseThrow(() -> new EntityNotFoundException("Supervisor not found"));
        return thesisApplicationRepository.findBySupervisor(supervisor).stream()
                .map(thesisApplicationMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ThesisApplicationDto updateStatus(Long applicationId, ThesisApplicationStatusType status) {
        ThesisApplication application = thesisApplicationRepository.findById(applicationId)
            .orElseThrow(() -> new EntityNotFoundException("Thesis application not found"));
            
        application.setStatus(status);
        application = thesisApplicationRepository.save(application);
        return thesisApplicationMapper.toDto(application);
    }
}
