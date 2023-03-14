package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        if (studentDto == null) return null;
        studentDto.setId(null);
        Faculty faculty = facultyRepository.findById(studentDto.getFacultyId()).orElse(null);
        if (faculty == null) return null;
        Student student = studentDto.toStudent();
        student.setFaculty(faculty);
        return StudentDto.fromStudent(studentRepository.save(student));
    }

    @Override
    public StudentDto getStudent(Long id) {
        return StudentDto.fromStudent(studentRepository.findById(id).orElse(null));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto dto = StudentDto.fromStudent(student);
            studentDtos.add(dto);
        }
        return studentDtos;
    }

    @Override
    public StudentDto editStudent(StudentDto studentDto) {
        if (studentDto == null) return null;
        Faculty faculty = facultyRepository.findById(studentDto.getFacultyId()).orElse(null);
        if (faculty == null) return null;
        studentDto.setId(null);
        Student student = studentDto.toStudent();
        student.setFaculty(faculty);
        return StudentDto.fromStudent(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getStudentsByAge(Integer age) {
        List<Student> students = studentRepository.findAllByAge(age);
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto dto = StudentDto.fromStudent(student);
            studentDtos.add(dto);
        }
        return studentDtos;
    }

    @Override
    public FacultyDto getFaculty(Long id) {
        return FacultyDto.fromFaculty(facultyRepository.findByStudentsId(id));
    }

    @Override
    public List<StudentDto> findByAgeBetween(Integer minAge, Integer maxAge) {
        List<Student> students = studentRepository.findByAgeBetween(minAge, maxAge);
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto dto = StudentDto.fromStudent(student);
            studentDtos.add(dto);
        }
        return studentDtos;
    }
}
