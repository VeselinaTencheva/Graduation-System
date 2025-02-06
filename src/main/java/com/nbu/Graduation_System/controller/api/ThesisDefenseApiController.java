package com.nbu.Graduation_System.controller.api;

import com.nbu.Graduation_System.dto.ThesisDefenseDto;
import com.nbu.Graduation_System.entity.Teacher;
import com.nbu.Graduation_System.service.thesis.ThesisDefenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/thesis-defenses")
public class ThesisDefenseApiController {
    
    private final ThesisDefenseService thesisDefenseService;

    public ThesisDefenseApiController(ThesisDefenseService thesisDefenseService) {
        this.thesisDefenseService = thesisDefenseService;
    }

    @GetMapping
    public ResponseEntity<List<ThesisDefenseDto>> getAllDefenses() {
        return ResponseEntity.ok(thesisDefenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisDefenseDto> getDefenseById(@PathVariable Long id) {
        return thesisDefenseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ThesisDefenseDto> createDefense(@RequestBody ThesisDefenseDto defenseDto) {
        return ResponseEntity.ok(thesisDefenseService.save(defenseDto));
    }

    @PostMapping("/schedule")
    public ResponseEntity<ThesisDefenseDto> scheduleDefense(
            @RequestParam Long thesisId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime defenseDate,
            @RequestBody Set<Teacher> committee) {
        return ResponseEntity.ok(thesisDefenseService.scheduleDefense(thesisId, defenseDate, committee));
    }

    @PatchMapping("/{id}/grade")
    public ResponseEntity<ThesisDefenseDto> gradeDefense(
            @PathVariable Long id,
            @RequestParam Double grade) {
        return ResponseEntity.ok(thesisDefenseService.gradeDefense(id, grade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisDefenseDto> updateDefense(
            @PathVariable Long id,
            @RequestBody ThesisDefenseDto defenseDto) {
        if (!thesisDefenseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        defenseDto.setId(id);
        return ResponseEntity.ok(thesisDefenseService.save(defenseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefense(@PathVariable Long id) {
        if (!thesisDefenseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        thesisDefenseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
