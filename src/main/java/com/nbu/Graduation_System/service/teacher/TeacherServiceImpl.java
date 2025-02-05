package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.dto.TeacherDto;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.mapper.TeacherMapper;
import com.nbu.Graduation_System.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, 
                            TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDto save(TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public Optional<TeacherDto> findById(Long id) {
        return teacherRepository.findById(id)
                .map(teacherMapper::toDto);
    }

    @Override
    public List<TeacherDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }
}
