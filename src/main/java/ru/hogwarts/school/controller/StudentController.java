package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.service.HouseService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final HouseService houseService;

    public StudentController(StudentService studentService, HouseService houseService) {
        this.studentService = studentService;
        this.houseService = houseService;
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDto>> showAllStudents(@RequestParam(name = "age", required = false) Integer age) {
        List<StudentDto> students;
        if (age == null) {
            students = studentService.getAllStudents();
        } else {
            students = studentService.getStudentsByAge(age);
        }
        if (students.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    ResponseEntity<StudentDto> showStudent(@PathVariable Long id) {
        StudentDto student = studentService.getStudent(id);
        if (student == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/faculty")
    ResponseEntity<FacultyDto> getFaculty(@PathVariable Long id) {
        FacultyDto facultyDto = houseService.findByStudentsId(id);
        if (facultyDto == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(facultyDto);
    }

    @GetMapping("/age")
    public ResponseEntity<List<StudentDto>> findByAge(@RequestParam(name = "minAge") Integer minAge,
                                                      @RequestParam(name = "maxAge") Integer maxAge) {
        List<StudentDto> studentDtos = studentService.findByAgeBetween(minAge, maxAge);
        if (studentDtos.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(studentDtos);
    }

    @PostMapping("")
    public StudentDto addStudent(@RequestBody StudentDto student) {
        return studentService.addStudent(student);
    }

    @PutMapping("")
    public ResponseEntity<StudentDto> editStudent(@RequestBody StudentDto student) {
        StudentDto editedStudent = studentService.editStudent(student);
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
