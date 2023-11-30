package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long idCount = 0L;

    public Student add(Student student) {
        student.setId(idCount);
        students.put(idCount,student);
        idCount++;
        return student;
    }

    public Student get(Long id) {
        return students.get(id);
    }

    public Collection<Student> getByAge(int age) {
        return students.values()
                .stream().
                filter(s -> s.getAge() == age )
                .collect(Collectors.toList());
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public Student update(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(),student);
            return student;
        } else {
            return null;
        }
    }

    public Student delete(Long id) {
        if (students.containsKey(id)) {
            Student returnStudent = students.get(id);
            students.remove(id);
            return returnStudent;
        } else {
            return null;
        }
    }
}
