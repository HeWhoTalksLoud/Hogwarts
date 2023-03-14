package ru.hogwarts.school.dto;

import lombok.Data;
import ru.hogwarts.school.model.Faculty;


@Data
public class FacultyDto {
        private Long id;
        private String name;
        private String color;
        public static FacultyDto fromFaculty(Faculty faculty) {
            if (faculty == null) return null;
            FacultyDto dto = new FacultyDto();
            dto.setId(faculty.getId());
            dto.setName(faculty.getName());
            dto.setColor(faculty.getColor());
            return dto;
        }
        public Faculty toFaculty() {
            Faculty faculty = new Faculty();
            faculty.setId(this.getId());
            faculty.setName(this.getName());
            faculty.setColor(this.getColor());
            return faculty;
        }

}
