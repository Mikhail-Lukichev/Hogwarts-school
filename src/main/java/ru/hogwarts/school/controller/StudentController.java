package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student foundStudent = studentService.get(id);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Student foundStudent = studentService.get(id);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentService.getFaculty(id));
    }

    @GetMapping("age/{age}")
    public Collection<Student> getByAge(@PathVariable int age) {
        return studentService.getByAge(age);
    }
    @GetMapping("age/{minAge}/{maxAge}")
    public Collection<Student> getByMinMaxAge(@PathVariable int minAge,@PathVariable int maxAge) {
        return studentService.getByMinMaxAge(minAge,maxAge);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student foundStudent = studentService.update(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(Long id) {
        Student foundStudent = studentService.get(id);
        if (foundStudent == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentService.delete(id);
        return ResponseEntity.ok(foundStudent);
    }
}
