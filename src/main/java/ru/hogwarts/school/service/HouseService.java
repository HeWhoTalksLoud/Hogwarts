package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface HouseService {
    Faculty addFaculty(Faculty faculty);
    Faculty getFaculty(Long id);
    List<Faculty> getAllFaculties();
    Faculty editFaculty(Faculty faculty);
    Faculty deleteFaculty(Long id);
    List<Faculty> getFacultiesByColor(String color);
}
