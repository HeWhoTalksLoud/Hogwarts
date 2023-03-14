package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("")
    public ResponseEntity<List<FacultyDto>> showAllFaculties(@RequestParam(name = "color", required = false) String color) {
        List<FacultyDto> facultyDtos;
        if (color == null) {
            facultyDtos = houseService.getAllFaculties();
        } else {
            facultyDtos = houseService.getFacultiesByColor(color);
        }
        if (facultyDtos.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(facultyDtos);
    }

    @GetMapping("/{id}")
    ResponseEntity<FacultyDto> showFaculty(@PathVariable Long id) {
        FacultyDto facultyDto = houseService.getFaculty(id);
        if (facultyDto == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(facultyDto);
    }

    @GetMapping("/{id}/students")
    ResponseEntity<List<StudentDto>> getStudents(@PathVariable Long id) {
        List<StudentDto> studentDtos = houseService.getStudents(id);
        if (studentDtos.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/search/{name}")
    ResponseEntity<List<FacultyDto>> findByNameIgnoreCase(@PathVariable String name) {
        List<FacultyDto> facultyDtos = houseService.findByNameIgnoreCase(name);
        if (facultyDtos.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(facultyDtos);
    }


    @PostMapping("")
    public FacultyDto addFaculty(@RequestBody FacultyDto facultyDto) {
        return houseService.addFaculty(facultyDto);
    }

    @PutMapping("")
    public ResponseEntity<FacultyDto> editFaculty(@RequestBody FacultyDto facultyDto) {
        FacultyDto editedFaculty = houseService.editFaculty(facultyDto);
        if (editedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.ok(editedFaculty);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
