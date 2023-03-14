package ru.hogwarts.school.service;

import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;

import java.util.List;

public interface HouseService {
    FacultyDto addFaculty(FacultyDto facultyDto);
    FacultyDto getFaculty(Long id);
    List<FacultyDto> getAllFaculties();
    FacultyDto editFaculty(FacultyDto facultyDto);
    void deleteFaculty(Long id);
    List<FacultyDto> getFacultiesByColor(String color);
    List<StudentDto> getStudents(Long id);
    List<FacultyDto> findByNameIgnoreCase(String name);
    FacultyDto findByStudentsId(Long id);
}
