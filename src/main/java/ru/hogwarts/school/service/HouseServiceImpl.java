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
public class HouseServiceImpl implements HouseService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public FacultyDto addFaculty(FacultyDto facultyDto) {
        if (facultyDto == null) return null;
        facultyDto.setId(null);
        return FacultyDto.fromFaculty(facultyRepository.save(facultyDto.toFaculty()));
    }

    @Override
    public FacultyDto getFaculty(Long id) {
        return FacultyDto.fromFaculty(facultyRepository.findById(id).orElse(null));
    }

    @Override
    public List<FacultyDto> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDto> facultyDtos = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDto dto = FacultyDto.fromFaculty(faculty);
            facultyDtos.add(dto);
        }
        return facultyDtos;
    }

    @Override
    public FacultyDto editFaculty(FacultyDto facultyDto) {
        if (facultyDto == null) return null;
        return FacultyDto.fromFaculty(facultyRepository.save(facultyDto.toFaculty()));
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<FacultyDto> getFacultiesByColor(String color) {
        List<Faculty> faculties = facultyRepository.findAllByColor(color);
        List<FacultyDto> facultyDtos = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDto dto = FacultyDto.fromFaculty(faculty);
            facultyDtos.add(dto);
        }
        return facultyDtos;
    }

    @Override
    public List<StudentDto> getStudents(Long id) {

        List<Student> students = studentRepository.findByFacultyId(id);

        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto dto = StudentDto.fromStudent(student);
            studentDtos.add(dto);
        }
        return studentDtos;
    }

    @Override
    public List<FacultyDto> findByNameIgnoreCase(String name) {
        List<Faculty> faculties = facultyRepository.findByNameIgnoreCase(name);

        List<FacultyDto> facultyDtos = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDto dto = FacultyDto.fromFaculty(faculty);
            facultyDtos.add(dto);
        }
        return facultyDtos;
    }

    @Override
    public FacultyDto findByStudentsId(Long id) {
        return FacultyDto.fromFaculty(facultyRepository.findByStudentsId(id));
    }
}
