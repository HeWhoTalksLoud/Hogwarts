package ru.hogwarts.school.dto;

import lombok.Data;
import ru.hogwarts.school.model.Student;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private Long facultyId;
    public static StudentDto fromStudent(Student student) {
        if (student == null) return null;
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        return dto;
    }

    public Student toStudent() {
        Student student = new Student();
        student.setId(this.getId());
        student.setName(this.getName());
        student.setAge(this.getAge());
        //student.setFaculty(this.);
        return student;
    }

}
