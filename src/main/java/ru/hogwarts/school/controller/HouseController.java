package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class HouseController {
    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/all")
    List<Faculty> showAllFaculties() {
        return houseService.getAllFaculties();
    }

    @GetMapping("/{id}")
    Faculty showFaculty(@PathVariable Long id) {
        return houseService.getFaculty(id);
    }

    @PostMapping("")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return houseService.addFaculty(faculty);
    }

    @PutMapping("")
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return houseService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        houseService.deleteFaculty(id);
    }

    @GetMapping("/color/{color}")
    List<Faculty> getFacultiesByColor(@PathVariable String color) {
        return houseService.getFacultiesByColor(color);
    }
}
