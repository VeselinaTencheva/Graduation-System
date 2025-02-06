// package com.nbu.Graduation_System.controller.api;

// import com.nbu.Graduation_System.dto.StudentDto;
// import com.nbu.Graduation_System.service.student.StudentService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/students")
// public class StudentApiController {
    
//     private final StudentService studentService;

//     public StudentApiController(StudentService studentService) {
//         this.studentService = studentService;
//     }

//     @GetMapping
//     public ResponseEntity<List<StudentDto>> getAllStudents() {
//         return ResponseEntity.ok(studentService.findAll());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
//         return studentService.findById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @GetMapping("/faculty-number/{facultyNumber}")
//     public ResponseEntity<StudentDto> getStudentByFacultyNumber(@PathVariable String facultyNumber) {
//         return studentService.findByFacultyNumber(facultyNumber)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
//         return ResponseEntity.ok(studentService.save(studentDto));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
//         if (!studentService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         studentDto.setId(id);
//         return ResponseEntity.ok(studentService.save(studentDto));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
//         if (!studentService.existsById(id)) {
//             return ResponseEntity.notFound().build();
//         }
//         studentService.deleteById(id);
//         return ResponseEntity.ok().build();
//     }
// }
