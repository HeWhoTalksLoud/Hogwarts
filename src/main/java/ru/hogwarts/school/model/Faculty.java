package ru.hogwarts.school.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;
}
