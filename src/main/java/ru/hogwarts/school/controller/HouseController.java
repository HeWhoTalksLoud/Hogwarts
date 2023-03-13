package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
    public ResponseEntity<List<Faculty>> showAllFacultys(@RequestParam(name = "color", required = false) String color) {
        List<Faculty> faculties;
        if (color == null) {
            faculties = houseService.getAllFaculties();
        } else {
            faculties = houseService.getFacultiesByColor(color);
        }
        if (faculties.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(faculties);
    }

    @GetMapping("/{id}")
    ResponseEntity<Faculty> showFaculty(@PathVariable String id) {
        Faculty student = houseService.getFaculty(Long.parseLong(id));
        if (student == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(student);
    }

    @PostMapping("")
    public Faculty addFaculty(@RequestBody Faculty student) {
        return houseService.addFaculty(student);
    }

    @PutMapping("")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty student) {
        Faculty editedFaculty = houseService.editFaculty(student);
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
