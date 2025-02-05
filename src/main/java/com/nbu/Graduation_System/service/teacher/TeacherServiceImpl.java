package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.repository.ThesisApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherRepository teacherRepository;
    private final ThesisApplicationRepository thesisApplicationRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, 
                            ThesisApplicationRepository thesisApplicationRepository) {
        this.teacherRepository = teacherRepository;
        this.thesisApplicationRepository = thesisApplicationRepository;
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public Set<ThesisApplication> findSupervisedTheses(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return thesisApplicationRepository.findBySupervisor(teacher);
    }
}
