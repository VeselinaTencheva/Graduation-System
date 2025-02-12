package com.nbu.Graduation_System.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.nbu.Graduation_System.dto.thesis_application.CreateThesisApplicationDto;
import com.nbu.Graduation_System.entity.ThesisApplication;

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
