package com.nbu.Graduation_System.service.student;

import com.nbu.Graduation_System.dto.student.*;
import com.nbu.Graduation_System.entity.Student;
import com.nbu.Graduation_System.repository.StudentRepository;
import com.nbu.Graduation_System.util.MapperUtil;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final MapperUtil mapperUtil;

   @Override
    public StudentDto findById(Long id) {
        return mapperUtil.getModelMapper().map(
                studentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student with id=" + id + " not found!")),
                StudentDto.class);
    }

    @Override
    public List<StudentDto> findAll() {
        return mapperUtil.mapList(studentRepository.findAll(), StudentDto.class);
    }

    @Override
    public List<StudentDto> findAllEligibleForThesisApplication() {
        return mapperUtil.mapList(studentRepository.findAllEligibleForThesisApplication(), StudentDto.class);
    }

    @Override
    public StudentDto save(CreateStudentDto studentDto) {
        Student student = mapperUtil.getModelMapper().map(studentDto, Student.class);
        student = studentRepository.save(student);
        return mapperUtil.getModelMapper().map(student, StudentDto.class);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    // @Override
    // public StudentDto updateMedicine(Medicine medicine, long id) {
    //     return this.medicineRepository.findById(id)
    //             .map(medicine1 -> {
    //                 medicine1.setName(medicine.getName());
    //                 return this.medicineRepository.save(medicine1);
    //             }).orElseGet(() ->
    //                     this.medicineRepository.save(medicine)
    //             );
    // }


    // @Override
    // public Optional<StudentDto> findByFacultyNumber(String facultyNumber) {
    //     return studentRepository.findByFacultyNumber(facultyNumber)
    //             .map(studentMapper::toDto);
    // }
}
