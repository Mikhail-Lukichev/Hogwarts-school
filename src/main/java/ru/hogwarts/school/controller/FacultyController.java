package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;
    private final StudentService studentService;

    public FacultyController(FacultyService facultyService, StudentService studentService) {
        this.facultyService = facultyService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Faculty> get(@PathVariable("id") Long id) {
        Faculty foundFaculty = facultyService.get(id);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping("/longestName")
    @ResponseBody
    public ResponseEntity<String> getLongestName() {
        String longestName = facultyService.getLongestName();
        if (longestName == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(longestName);
    }

    @GetMapping("/{id}/students")
    @ResponseBody
    public ResponseEntity<Collection<Student>>  getStudents(@PathVariable("id") Long id) {
        Faculty foundFaculty = facultyService.get(id);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentService.getByFacultyId(id));
    }

    @GetMapping("/color/{color}")
    @ResponseBody
    public Collection<Faculty> getByColor(@PathVariable("color") String color) {
        return facultyService.getByColor(color);
    }

    @GetMapping("/search/{search}")
    @ResponseBody
    public Collection<Faculty> getBySearchString(@PathVariable("search") String search) {
        return facultyService.getBySearchString(search);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.update(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> delete(@PathVariable("id") Long id) {
        Faculty foundFaculty = facultyService.get(id);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        facultyService.delete(id);
        return ResponseEntity.ok(foundFaculty);
    }
}
