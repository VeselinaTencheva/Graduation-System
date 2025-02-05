package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.repository.ThesisApplicationRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {
    
    private final ThesisApplicationRepository thesisApplicationRepository;

    public ThesisApplicationServiceImpl(ThesisApplicationRepository thesisApplicationRepository) {
        this.thesisApplicationRepository = thesisApplicationRepository;
    }

    @Override
    public ThesisApplication save(ThesisApplication application) {
        return thesisApplicationRepository.save(application);
    }

    @Override
    public Optional<ThesisApplication> findById(Long id) {
        return thesisApplicationRepository.findById(id);
    }

    @Override
    public List<ThesisApplication> findAll() {
        return thesisApplicationRepository.findAll();
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
    public Set<ThesisApplication> findByStudent(Student student) {
        return thesisApplicationRepository.findByStudent(student);
    }

    @Override
    public Set<ThesisApplication> findBySupervisor(Teacher supervisor) {
        return thesisApplicationRepository.findBySupervisor(supervisor);
    }

    @Override
    public ThesisApplication updateStatus(Long id, ThesisApplicationStatusType status) {
        ThesisApplication application = thesisApplicationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ThesisApplication not found"));
        application.setStatus(status);
        return thesisApplicationRepository.save(application);
    }
}
