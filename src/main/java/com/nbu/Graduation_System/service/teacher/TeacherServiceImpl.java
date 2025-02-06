package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.dto.teacher.*;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherRepository teacherRepository;
    private final MapperUtil mapperUtil;

   @Override
    public TeacherDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                teacherRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Teacher with id=" + id + " not found!")),
                TeacherDto.class);
    }

    @Override
    public List<TeacherDto> findAll() {
        return mapperUtil.mapList(teacherRepository.findAll(), TeacherDto.class);
    }

    @Override
    public TeacherDto save(CreateTeacherDto teacherDto) {
        Teacher teacher = mapperUtil.getModelMapper().map(teacherDto, Teacher.class);
        teacher = teacherRepository.save(teacher);
        return mapperUtil.getModelMapper().map(teacher, TeacherDto.class);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }
}
