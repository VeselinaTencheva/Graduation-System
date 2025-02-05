package com.nbu.Graduation_System.service.thesis;

import com.nbu.Graduation_System.entity.Thesis;
import com.nbu.Graduation_System.entity.ThesisApplication;
import java.util.List;
import java.util.Optional;

public interface ThesisService {
    Thesis save(Thesis thesis);
    Optional<Thesis> findById(Long id);
    List<Thesis> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Thesis createFromApplication(ThesisApplication application);
}
