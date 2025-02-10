// package com.nbu.Graduation_System.controller.api;

// import com.nbu.Graduation_System.dto.teacher.TeacherDto;
// import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
// import com.nbu.Graduation_System.service.teacher.TeacherService;
// import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
// import lombok.AllArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/teachers")
// @AllArgsConstructor
// public class TeacherApiController {
    
//     private final TeacherService teacherService;
//     private final ThesisApplicationService thesisApplicationService;

//     @GetMapping
//     public ResponseEntity<List<TeacherDto>> getAllTeachers() {
//         return ResponseEntity.ok(teacherService.findAll());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
//         return teacherService.findById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @GetMapping("/{id}/theses")
//     public ResponseEntity<List<ThesisApplicationDto>> getTeacherTheses(@PathVariable Long id) {
//         return ResponseEntity.ok(thesisApplicationService.findBySupervisorId(id));
//     }

//     @PostMapping
//     public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
//         return ResponseEntity.ok(teacherService.save(teacherDto));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
//         if (!teacherService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         teacherDto.setId(id);
//         return ResponseEntity.ok(teacherService.save(teacherDto));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
//         if (!teacherService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         teacherService.deleteById(id);
//         return ResponseEntity.ok().build();
//     }
// }
