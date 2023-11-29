package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long idCount = 0L;

    public Faculty add(Faculty faculty) {
        faculty.setId(idCount);
        faculties.put(idCount,faculty);
        idCount++;
        return faculty;
    }

    public Faculty get(Long id) {
        return faculties.get(id);
    }

    public Collection<Faculty> getByColor(String color) {
        return faculties.values()
                .stream().
                filter(f -> Objects.equals(f.getColor(), color))
                .collect(Collectors.toList());
    }

    public Collection<Faculty> getAll() {
        return faculties.values();
    }

    public Faculty update(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(),faculty);
            return faculty;
        } else {
            return null;
        }
    }

    public Faculty delete(Long id) {
        if (faculties.containsKey(id)) {
            Faculty returnFaculty = faculties.get(id);
            faculties.remove(id);
            return returnFaculty;
        } else {
            return null;
        }
    }
}
