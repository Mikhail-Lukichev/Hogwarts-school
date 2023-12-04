package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Faculty getFaculty(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        assert student != null;
        return student.getFaculty();
    }

    public Collection<Student> getByFacultyId(Long id) {
        return studentRepository.findByFaculty_id(id);
    }

    public Collection<Student> getByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getByMinMaxAge(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge,maxAge);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student update(Student student) {
        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
            return student;
        } else {
            return null;
        }
    }

    public Student delete(Long id) {
        if (studentRepository.existsById(id)) {
            Student returnStudent = studentRepository.findById(id).orElse(null);
            studentRepository.deleteById(id);
            return returnStudent;
        } else {
            return null;
        }
    }
}
