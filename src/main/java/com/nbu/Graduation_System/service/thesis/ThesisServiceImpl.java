package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.repository.ThesisRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ThesisServiceImpl implements ThesisService {
    
    private final ThesisRepository thesisRepository;

    public ThesisServiceImpl(ThesisRepository thesisRepository) {
        this.thesisRepository = thesisRepository;
    }

    @Override
    public Thesis save(Thesis thesis) {
        return thesisRepository.save(thesis);
    }

    @Override
    public Optional<Thesis> findById(Long id) {
        return thesisRepository.findById(id);
    }

    @Override
    public List<Thesis> findAll() {
        return thesisRepository.findAll();
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
    public Thesis createFromApplication(ThesisApplication application) {
        Thesis thesis = new Thesis();
        thesis.setTitle(application.getTitle());
        thesis.setThesisApplication(application);
        thesis.setSubmissionDate(LocalDateTime.now());
        return thesisRepository.save(thesis);
    }
}
