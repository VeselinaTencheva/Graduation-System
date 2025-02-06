// package com.nbu.Graduation_System.controller.api;

// import com.nbu.Graduation_System.dto.thesis_application.ThesisApplicationDto;
// import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
// import com.nbu.Graduation_System.service.thesis.ThesisApplicationService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Set;

// @RestController
// @RequestMapping("/api/thesis-applications")
// public class ThesisApplicationApiController {
    
//     private final ThesisApplicationService thesisApplicationService;

//     public ThesisApplicationApiController(ThesisApplicationService thesisApplicationService) {
//         this.thesisApplicationService = thesisApplicationService;
//     }

//     @GetMapping
//     public ResponseEntity<List<ThesisApplicationDto>> getAllApplications() {
//         return ResponseEntity.ok(thesisApplicationService.findAll());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<ThesisApplicationDto> getApplicationById(@PathVariable Long id) {
//         return thesisApplicationService.findById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @GetMapping("/student/{studentId}")
//     public ResponseEntity<Set<ThesisApplicationDto>> getApplicationsByStudent(@PathVariable Long studentId) {
//         return ResponseEntity.ok(thesisApplicationService.findByStudentId(studentId));
//     }

//     @GetMapping("/supervisor/{supervisorId}")
//     public ResponseEntity<Set<ThesisApplicationDto>> getApplicationsBySupervisor(@PathVariable Long supervisorId) {
//         return ResponseEntity.ok(thesisApplicationService.findBySupervisorId(supervisorId));
//     }

//     @PostMapping
//     public ResponseEntity<ThesisApplicationDto> createApplication(@RequestBody ThesisApplicationDto applicationDto) {
//         return ResponseEntity.ok(thesisApplicationService.save(applicationDto));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<ThesisApplicationDto> updateApplication(@PathVariable Long id, 
//                                                                 @RequestBody ThesisApplicationDto applicationDto) {
//         if (!thesisApplicationService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         applicationDto.setId(id);
//         return ResponseEntity.ok(thesisApplicationService.save(applicationDto));
//     }

//     @PatchMapping("/{id}/status")
//     public ResponseEntity<ThesisApplicationDto> updateApplicationStatus(@PathVariable Long id, 
//                                                                       @RequestParam ThesisApplicationStatusType status) {
//         return ResponseEntity.ok(thesisApplicationService.updateStatus(id, status));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
//         if (!thesisApplicationService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         thesisApplicationService.deleteById(id);
//         return ResponseEntity.ok().build();
//     }
// }
