package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> showAllStudents(@RequestParam(name = "age", required = false) String age) {
        List<Student> students;
        if (age == null) {
            students = studentService.getAllStudents();
        } else {
            students = studentService.getStudentsByAge(Integer.parseInt(age));
        }
        if (students.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    ResponseEntity<Student> showStudent(@PathVariable String id) {
        Student student = studentService.getStudent(Long.parseLong(id));
        if (student == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(student);
    }

    @PostMapping("")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editedStudent = studentService.editStudent(student);
        if (editedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.ok(editedStudent);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
