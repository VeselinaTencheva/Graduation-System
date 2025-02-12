package com.nbu.Graduation_System.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.nbu.Graduation_System.dto.thesis_application.CreateThesisApplicationDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.student.StudentDto;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.viewmodel.thesis_application.ThesisApplicationViewModel;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Configuration
public class MapperUtil {
    private final ModelMapper modelMapper;

    public MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setSkipNullEnabled(true);
            
        // Configure to handle Student <-> ThesisApplication circular reference
        this.modelMapper.getConfiguration()
            .setAmbiguityIgnored(true)
            .setPreferNestedProperties(false);
            
        // Configure mapping for ThesisApplication
        this.modelMapper.typeMap(CreateThesisApplicationDto.class, ThesisApplication.class)
            .addMappings(mapper -> {
                mapper.skip(ThesisApplication::setId);
                mapper.skip(ThesisApplication::setThesis);
                mapper.skip(ThesisApplication::setStudent);
            });
            
        // Configure mapping for ThesisApplicationDto -> ThesisApplicationViewModel
        this.modelMapper.typeMap(ThesisApplicationDto.class, ThesisApplicationViewModel.class)
            .addMappings(mapper -> {
                mapper.map(ThesisApplicationDto::getSupervisor, ThesisApplicationViewModel::setSupervisor);
                mapper.map(ThesisApplicationDto::getStudent, ThesisApplicationViewModel::setStudent);
                mapper.map(ThesisApplicationDto::getThesis, ThesisApplicationViewModel::setThesis);
            });
            
        // Configure mapping for TeacherDto -> TeacherViewModel
        this.modelMapper.typeMap(TeacherDto.class, TeacherViewModel.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getName(), TeacherViewModel::setName);
                mapper.map(src -> src.getEmail(), TeacherViewModel::setEmail);
                mapper.map(src -> src.getId(), TeacherViewModel::setId);
            });
            
        // Configure mapping for StudentDto -> StudentViewModel
        this.modelMapper.typeMap(StudentDto.class, StudentViewModel.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getName(), StudentViewModel::setName);
                mapper.map(src -> src.getEmail(), StudentViewModel::setEmail);
                mapper.map(src -> src.getId(), StudentViewModel::setId);
            });
    }

    @Bean
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
