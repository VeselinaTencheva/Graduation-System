package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.dto.thesis.CreateThesisDto;
import com.nbu.Graduation_System.dto.thesis.ThesisDto;
import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.repository.ThesisRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ThesisServiceImpl implements ThesisService {
    
    private final ThesisRepository thesisRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ThesisDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                thesisRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Thesis with id=" + id + " not found!")),
                ThesisDto.class);
    }

    @Override
    public List<ThesisDto> findAll() {
        return mapperUtil.mapList(thesisRepository.findAll(), ThesisDto.class);
    }

    @Override
    public ThesisDto save(CreateThesisDto thesisDto) {
        Thesis thesis = mapperUtil.getModelMapper().map(thesisDto, Thesis.class);
        thesis = thesisRepository.save(thesis);
        return mapperUtil.getModelMapper().map(thesis, ThesisDto.class);
    }

    @Override
    public boolean existsById(Long id) {
        return thesisRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        thesisRepository.deleteById(id);
    }

    @Override
    public ThesisDto createFromApplication(ThesisApplication application) {
        Thesis thesis = new Thesis();
        thesis.setThesisApplication(application);
        thesis = thesisRepository.save(thesis);
        return mapperUtil.getModelMapper().map(thesis, ThesisDto.class);
    }
}
