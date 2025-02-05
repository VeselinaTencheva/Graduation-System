package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.ThesisDto;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.mapper.ThesisMapper;
import com.nbu.Graduation_System.repository.ThesisRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThesisServiceImpl implements ThesisService {
    
    private final ThesisRepository thesisRepository;
    private final ThesisMapper thesisMapper;

    public ThesisServiceImpl(ThesisRepository thesisRepository,
                           ThesisMapper thesisMapper) {
        this.thesisRepository = thesisRepository;
        this.thesisMapper = thesisMapper;
    }

    @Override
    public ThesisDto save(ThesisDto thesisDto) {
        Thesis thesis = thesisMapper.toEntity(thesisDto);
        thesis = thesisRepository.save(thesis);
        return thesisMapper.toDto(thesis);
    }

    @Override
    public Optional<ThesisDto> findById(Long id) {
        return thesisRepository.findById(id)
                .map(thesisMapper::toDto);
    }

    @Override
    public List<ThesisDto> findAll() {
        return thesisRepository.findAll().stream()
                .map(thesisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        thesisRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return thesisRepository.existsById(id);
    }

    @Override
    public ThesisDto createFromApplication(ThesisApplication application) {
        Thesis thesis = new Thesis();
        thesis.setTitle(application.getTitle());
        thesis.setThesisApplication(application);
        thesis.setSubmissionDate(LocalDateTime.now());
        thesis = thesisRepository.save(thesis);
        return thesisMapper.toDto(thesis);
    }
}
