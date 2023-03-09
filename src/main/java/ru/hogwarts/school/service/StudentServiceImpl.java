package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private HashMap<Long, Student> students = new HashMap<>();
    private Long lastId = 0L;
    @Override
    public Student addStudent(Student student) {
        student.setId(++lastId);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student getStudent(Long id) {
        return students.get(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public List<Student> getStudentsByAge(Integer age) {
        return students.values()
                .stream()
                .filter(student -> student.getAge().equals(age))
                .collect(Collectors.toList());
    }
}
