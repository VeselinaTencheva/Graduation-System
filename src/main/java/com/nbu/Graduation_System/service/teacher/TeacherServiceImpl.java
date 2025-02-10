package com.nbu.Graduation_System.service.teacher;

import com.nbu.Graduation_System.dto.teacher.*;
import com.nbu.Graduation_System.dto.thesis_defense.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.repository.TeacherRepository;
import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
import com.nbu.Graduation_System.service.thesis.ThesisDefenseService;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherRepository teacherRepository;
    private final ThesisApplicationService thesisApplicationService;
    private final ThesisDefenseService thesisDefenseService;
    private final MapperUtil mapperUtil;

    @Override
    public TeacherDto findById(Long id) {
        TeacherDto teacher = mapperUtil.getModelMapper().map(
                teacherRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Teacher with id=" + id + " not found!")),
                TeacherDto.class);

        // Map supervised thesis applications
        final List<ThesisApplicationViewModel> supervisedTheses = mapperUtil.mapList(thesisApplicationService
                .findBySupervisorId(id), ThesisApplicationViewModel.class);
        teacher.setSupervisedThesesNames(String.join(", ", supervisedTheses.stream()
                .map(ThesisApplicationViewModel::getTitle)
                .toList()));

        // Map thesis defenses
        final List<ThesisDefenseDto> thesisDefenses = thesisDefenseService.findByTeacherId(id);
        teacher.setThesisDefensesNames(String.join(", ", thesisDefenses.stream()
                .map(defense -> defense.getThesis().getTitle())
                .toList()));

        return teacher;
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
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }
}
