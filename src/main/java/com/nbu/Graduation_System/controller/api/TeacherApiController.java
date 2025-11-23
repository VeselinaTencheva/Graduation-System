package com.nbu.Graduation_System.controller.api;

import com.nbu.Graduation_System.dto.teacher.CreateTeacherDto;
import com.nbu.Graduation_System.dto.teacher.TeacherDto;
import com.nbu.Graduation_System.dto.teacher.UpdateTeacherDto;
import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
import com.nbu.Graduation_System.service.teacher.TeacherService;
import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherApiController {

    private final TeacherService teacherService;
    private final ThesisApplicationService thesisApplicationService;

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        if (!teacherService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping("/{id}/theses")
    public ResponseEntity<List<ThesisApplicationDto>> getTeacherTheses(@PathVariable Long id) {
        if (!teacherService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(thesisApplicationService.findBySupervisorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody CreateTeacherDto teacherDto) {
        TeacherDto created = teacherService.save(teacherDto);
        return ResponseEntity
                .created(URI.create("/api/teachers/" + created.getId()))
                .body(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTeacherDto teacherDto
    ) {
        if (!teacherService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        TeacherDto updated = teacherService.update(id, teacherDto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (!teacherService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
