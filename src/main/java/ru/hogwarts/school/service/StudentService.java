package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudent(Long id);
    List<Student> getAllStudents();
    Student editStudent(Student student);
    Student deleteStudent(Long id);
    List<Student> getStudentsByAge(Integer age);
}
