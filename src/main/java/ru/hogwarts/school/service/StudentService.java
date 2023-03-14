package ru.hogwarts.school.service;

import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto addStudent(StudentDto studentDto);
    StudentDto getStudent(Long id);
    List<StudentDto> getAllStudents();
    StudentDto editStudent(StudentDto student);
    void deleteStudent(Long id);
    List<StudentDto> getStudentsByAge(Integer age);
    List<StudentDto> findByAgeBetween(Integer minAge, Integer maxAge);
    FacultyDto getFaculty(Long id);
}
