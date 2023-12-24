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
    @ResponseBody
    public ResponseEntity<Collection<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Student> get(@PathVariable("id") Long id) {
        Student foundStudent = studentService.get(id);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @GetMapping("/nameStartsWithA")
    @ResponseBody
    public ResponseEntity<Collection<Student>> getWithNameStartA() {
        return ResponseEntity.ok(studentService.getWithNameStartA());
    }

    @GetMapping("/averageAgeStream")
    @ResponseBody
    public ResponseEntity<Double> getAverageAgeStream() {
        return ResponseEntity.ok(studentService.getAverageAgeStream());
    }

    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<Integer> countStudents() {
        return ResponseEntity.ok(studentService.count());
    }

    @GetMapping("/averageAge")
    @ResponseBody
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/lastFive")
    @ResponseBody
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }

    @GetMapping("/{id}/faculty")
    @ResponseBody
    public ResponseEntity<Faculty> getFaculty(@PathVariable("id") Long id) {
        Student foundStudent = studentService.get(id);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentService.getFaculty(id));
    }

    @GetMapping("/age/{age}")
    @ResponseBody
    public Collection<Student> getByAge(@PathVariable("age") int age) {
        return studentService.getByAge(age);
    }
    @GetMapping("/age/{minAge}/{maxAge}")
    @ResponseBody
    public Collection<Student> getByMinMaxAge(@PathVariable("minAge") int minAge,@PathVariable("maxAge") int maxAge) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id")Long id) {
        Student foundStudent = studentService.get(id);
        if (foundStudent == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentService.delete(id);
        return ResponseEntity.ok(foundStudent);
    }
}
