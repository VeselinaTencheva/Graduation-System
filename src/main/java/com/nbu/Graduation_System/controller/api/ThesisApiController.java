// package com.nbu.Graduation_System.controller.api;

// import com.nbu.Graduation_System.dto.thesis.ThesisDto;
// import com.nbu.Graduation_System.service.thesis.ThesisService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/theses")
// public class ThesisApiController {
    
//     private final ThesisService thesisService;

//     public ThesisApiController(ThesisService thesisService) {
//         this.thesisService = thesisService;
//     }

//     @GetMapping
//     public ResponseEntity<List<ThesisDto>> getAllTheses() {
//         return ResponseEntity.ok(thesisService.findAll());
//     }

//     // @GetMapping("/{id}")
//     // public ResponseEntity<ThesisDto> getThesisById(@PathVariable Long id) {
//     //     return thesisService.findById(id)
//     //             .map(ResponseEntity::ok)
//     //             .orElse(ResponseEntity.notFound().build());
//     // }

//     // @PostMapping
//     // public ResponseEntity<ThesisDto> createThesis(@RequestBody ThesisDto thesisDto) {
//     //     return ResponseEntity.ok(thesisService.save(thesisDto));
//     // }

//     // @PostMapping("/from-application")
//     // public ResponseEntity<ThesisDto> createThesisFromApplication(@RequestBody ThesisApplication application) {
//     //     return ResponseEntity.ok(thesisService.createFromApplication(application));
//     // }

//     // @PutMapping("/{id}")
//     // public ResponseEntity<ThesisDto> updateThesis(@PathVariable Long id, @RequestBody ThesisDto thesisDto) {
//     //     if (!thesisService.existsById(id)) {
//     //         return ResponseEntity.notFound().build();
//     //     }
//     //     thesisDto.setId(id);
//     //     return ResponseEntity.ok(thesisService.save(thesisDto));
//     // }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteThesis(@PathVariable Long id) {
//         if (!thesisService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         thesisService.deleteById(id);
//         return ResponseEntity.ok().build();
//     }
// }
