package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_application.CreateThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.repository.ThesisApplicationRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {
    
    private final ThesisApplicationRepository thesisApplicationRepository;
    private final MapperUtil mapperUtil;


     @Override
    public ThesisApplicationDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                thesisApplicationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ThesisDefense with id=" + id + " not found!")),
                ThesisApplicationDto.class);
    }

    @Override
    public List<ThesisApplicationDto> findAll() {
        return mapperUtil.mapList(thesisApplicationRepository.findAll(), ThesisApplicationDto.class);
    }

    @Override
    public List<ThesisApplicationDto> findBySupervisorId(Long id) {
        return mapperUtil.mapList(thesisApplicationRepository.findBySupervisorId(id), ThesisApplicationDto.class);
    }

    @Override
    public ThesisApplicationDto save(CreateThesisApplicationDto applicationDto) {
        ThesisApplication application = mapperUtil.getModelMapper().map(applicationDto, ThesisApplication.class);
        application = thesisApplicationRepository.save(application);
        return mapperUtil.getModelMapper().map(application, ThesisApplicationDto.class);
    }

    @Override
    public void deleteById(Long id) {
        thesisApplicationRepository.deleteById(id);
    }

    // @Override
    // public boolean existsById(Long id) {
    //     return thesisApplicationRepository.existsById(id);
    // }

    // @Override
    // public Set<ThesisApplicationDto> findByStudentId(Long studentId) {
    //     Student student = studentRepository.findById(studentId)
    //         .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    //     return thesisApplicationRepository.findByStudent(student).stream()
    //             .map(thesisApplicationMapper::toDto)
    //             .collect(Collectors.toSet());
    // }

    // @Override
    // public Set<ThesisApplicationDto> findBySupervisorId(Long supervisorId) {
    //     Teacher supervisor = teacherRepository.findById(supervisorId)
    //         .orElseThrow(() -> new EntityNotFoundException("Supervisor not found"));
    //     return thesisApplicationRepository.findBySupervisor(supervisor).stream()
    //             .map(thesisApplicationMapper::toDto)
    //             .collect(Collectors.toSet());
    // }

    // @Override
    // public ThesisApplicationDto updateStatus(Long applicationId, ThesisApplicationStatusType status) {
    //     ThesisApplication application = thesisApplicationRepository.findById(applicationId)
    //         .orElseThrow(() -> new EntityNotFoundException("Thesis application not found"));
            
    //     application.setStatus(status);
    //     application = thesisApplicationRepository.save(application);
    //     return thesisApplicationMapper.toDto(application);
    // }
}
