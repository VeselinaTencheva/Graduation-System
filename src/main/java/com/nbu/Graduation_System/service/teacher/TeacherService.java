package com.nbu.Graduation_System.service.teacher;

import java.util.List;

import com.nbu.Graduation_System.dto.teacher.CreateTeacherDto;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.teacher.UpdateTeacherDto;

public interface TeacherService {

    TeacherDto save(CreateTeacherDto teacher);

    TeacherDto findById(Long id);

    List<TeacherDto> findAllByDepartmentId(Long departmentId);

    List<TeacherDto> findAll();

    TeacherDto update(Long id, UpdateTeacherDto dto);

    void deleteById(Long id);

    boolean existsById(Long id);
}
