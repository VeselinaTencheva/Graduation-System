package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis_application.CreateThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.repository.ThesisApplicationRepository;
import com.nbu.Graduation_System.repository.StudentRepository;
import com.nbu.Graduation_System.repository.ThesisRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {
    
    private final ThesisApplicationRepository thesisApplicationRepository;
    private final StudentRepository studentRepository;
    private final ThesisRepository thesisRepository;
    private final MapperUtil mapperUtil;


     @Override
    public ThesisApplicationDto findById(Long id) {
        final ThesisApplication application =
                thesisApplicationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ThesisDefense with id=" + id + " not found!"));

        final ThesisApplicationDto dto = mapperUtil.getModelMapper().map(application, ThesisApplicationDto.class);
        return dto;
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

    @Override
    @Transactional
    public void approve(Long id) {
        ThesisApplication application = thesisApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thesis application not found"));
        
        if (application.getStatus() != ThesisApplicationStatusType.SUBMITTED) {
            throw new RuntimeException("Can only approve submitted applications");
        }

        // Check if current user is the supervisor
        if (isCurrentUserSupervisor(application)) {
            throw new RuntimeException("Supervisors cannot approve their own thesis applications");
        }
        
        // Create thesis when application is approved
        Thesis thesis = new Thesis();
        thesis.setUploadDate(LocalDateTime.now());
        thesis.setThesisApplication(application);
        thesisRepository.save(thesis);
        
        application.setStatus(ThesisApplicationStatusType.ACCEPTED);
        thesisApplicationRepository.save(application);
    }

    @Override
    public void reject(Long id) {
        ThesisApplication application = thesisApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thesis application not found"));
        
        if (application.getStatus() != ThesisApplicationStatusType.SUBMITTED) {
            throw new RuntimeException("Can only reject submitted applications");
        }

        // Check if current user is the supervisor
        if (isCurrentUserSupervisor(application)) {
            throw new RuntimeException("Supervisors cannot reject their own thesis applications");
        }
        
        application.setStatus(ThesisApplicationStatusType.REJECTED);
        thesisApplicationRepository.save(application);
    }

    private boolean isCurrentUserSupervisor(ThesisApplication application) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return application.getSupervisor().getId().equals(Long.parseLong(userDetails.getUsername()));
        }
        return false;
    }

    @Override
    @Transactional
    public ThesisApplicationDto create(CreateThesisApplicationDto thesisApplicationDto) {
        Student student = studentRepository.findById(thesisApplicationDto.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found"));
            
        ThesisApplication thesisApplication = mapperUtil.getModelMapper().map(thesisApplicationDto, ThesisApplication.class);
        thesisApplication.setStudent(student);
        thesisApplication.setSubmissionDate(LocalDateTime.now());
        thesisApplication.setStatus(ThesisApplicationStatusType.SUBMITTED);
        
        thesisApplication = thesisApplicationRepository.saveAndFlush(thesisApplication);
        
        thesisApplication = thesisApplicationRepository.findById(thesisApplication.getId())
            .orElseThrow(() -> new RuntimeException("Failed to create thesis application"));
            
        return mapperUtil.getModelMapper().map(thesisApplication, ThesisApplicationDto.class);
    }

    @Override
    @Transactional
    public ThesisApplicationDto approveWithStudentId(Long id) {
        ThesisApplication application = thesisApplicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Thesis application not found"));
        
        // Find and assign the student
        Student student = studentRepository.findById(application.getStudent().getId())
            .orElseThrow(() -> new RuntimeException("Student not found"));
            
        // Check if student already has an approved application
        if (thesisApplicationRepository.existsByStudentAndStatus(student, ThesisApplicationStatusType.ACCEPTED)) {
            throw new IllegalStateException("Student already has an approved thesis application");
        }
        
        application.setStatus(ThesisApplicationStatusType.ACCEPTED);
        application.setStudent(student);
        
        application = thesisApplicationRepository.save(application);
        return mapperUtil.getModelMapper().map(application, ThesisApplicationDto.class);
    }

    @Override
    public ThesisApplicationDto rejectWithStudentId(Long id) {
        ThesisApplication application = thesisApplicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Thesis application not found"));
        
        application.setStatus(ThesisApplicationStatusType.REJECTED);
        
        application = thesisApplicationRepository.save(application);
        return mapperUtil.getModelMapper().map(application, ThesisApplicationDto.class);
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
